(ns smackdown.generator
  (require [hiccup.core :refer (html)]
           [hiccup.page :refer [include-js include-css]]
           [hiccup.util :refer [escape-html]]
           [me.raynes.cegdown :refer (to-html)])
  )

(defn- template [& content]
 (html
   [:head
     [:title "TITLE - FIXME"]
     (include-css "http://fonts.googleapis.com/css?family=Open+Sans:300italic,300,400italic,400,600italic,600,700italic,700,800italic,800")
     (include-css "/css")
    ]
   [:body
    [:div.container
       [:h1 "johncowie.co.uk"]
       content
     ]
   ]
  )
  )

(defn generate-html [file]
  (template
  (let [file-name (str "resources/public/markdown/" file ".md")]
    (to-html (slurp file-name))
    )))
