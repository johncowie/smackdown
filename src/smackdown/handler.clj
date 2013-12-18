(ns smackdown.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [me.raynes.cegdown :as md]
            [hiccup.core :refer [html]]
            [hiccup.page :refer [include-css include-js]]
            [smackdown.generator :refer [generate-html]]
            [smackdown.css :refer [generate-css]]
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

(md/to-html
 (slurp "resources/public/markdown/test.md"))

(defroutes app-routes
  (GET "/md" [] (page))
  (GET "/convert" {params :params} (md/to-html (params :md)))
  (GET "/css" [] (generate-css))
  (GET "/:page" [page] (generate-html page))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
