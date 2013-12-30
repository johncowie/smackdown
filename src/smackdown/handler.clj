(ns smackdown.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :refer [redirect response content-type file-response]]
            [me.raynes.cegdown :as md]
            [hiccup.core :refer [html]]
            [hiccup.page :refer [include-css include-js]]
            [smackdown.generator :refer [generate-site!]]
            [smackdown.views :refer [post multiple-posts base-template]]
            [smackdown.css :refer [generate-css]]
            [clj-time.core :refer [date-time]]
            ))

(defn page []
  (html
     [:head
       (include-css "/css/main.css")
       (include-js "/js/jquery-2.0.3.min.js")
      ]
     [:body
       [:div
         [:textarea {:class "markdown-view"}
            (slurp "resources/public/markdown/test.md")
          ]
         [:div {:class "html-view"}]
       ]
       (include-js "/js/main.js")
     ]
   )
  )

(defn test-page []
  (multiple-posts "Test Title" [{:title "Test post"
         :html "In the beginning God created the heavens and the earth.  And he
         saw that it was super good and he was super chuffed with it - like, really
         chuffed.  He was flipping well pleased wa'nt he.
         <code class=\"lang-clojure\">(println \"Hello World!!\")</code>
         <code class=\"lang-java\">public static void main(){</br>  int bob = 2 + 2;</br>}</code>"
         :date (date-time 2013 12 25 15 0 0)
         :prev {:url "blah"}
         :next {:url "blah"}}
                                {:title "Test post 2"
         :html "In the beginning God created the heavens and the earth.  And he
         saw that it was super good and he was super chuffed with it - like, really
         chuffed.  He was flipping well pleased wa'nt he.
         <code class=\"lang-clojure\">(println \"Hello World!!\")</code>
         <code class=\"lang-java\">public static void main(){</br>  int bob = 2 + 2;</br>}</code>"
         :date (date-time 2013 12 23 15 0 0)
         :next {:url "blah"}}])
  )

(defn get-absolute-path [file]
  (str "file://" (.getAbsolutePath (clojure.java.io/file file))))

(defroutes app-routes
  (GET "/md" [] (page))
  (GET "/convert" {params :params} (md/to-html (params :md)))
  (GET "/generate" [] (do
                        (generate-site! "site")
                        (redirect "site/index.html")))
  (GET "/test" [] (test-page))
  (GET "/css" [] (content-type (response (generate-css)) "text/css"))
  (GET "/site/:file-name" [file-name] (file-response (str "site/" file-name)))
  (GET "/template" [] (base-template "Template" nil))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
