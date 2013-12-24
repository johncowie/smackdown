(ns smackdown.views
  (:require [hiccup.page :refer [include-js include-css html5]]
            [hiccup.core :refer [html]]
            [clj-time.format :refer [formatter unparse]])
  )

(def date-formatter (formatter "dd/MM/yyyy"))

(defn fdate [date] (unparse date-formatter date))

(defn base-template [title & content]
   (html5
   [:head
     [:title title]
     (include-css "http://fonts.googleapis.com/css?family=Open+Sans:300italic,300,400italic,400,600italic,600,700italic,700,800italic,800")
     (include-css "style.css")
     (include-css "js/google-code-prettify/prettify.css")
    ]
   [:body
    [:div.header
       [:span.container "JOHN COWIE"]]
    [:div.nav
       [:div.container
         [:ul
           [:li "Home"]
           [:li "About"]
          ]
        ]
     ]
    [:div.container
      content
     ]
   ]))

(defn- post-footer [prevPostLink nextPostLink tags]
  [:div.footer
    (if nextPostLink [:a.next {:href (:url nextPostLink)} "next"])
    (if prevPostLink [:a.previous {:href (:url prevPostLink)} "previous"])
     [:div.clear-fix]
     [:div.tags
       [:ul
         (for [tag tags]
           [:li [:a {:href (str tag ".html")} tag]]
           )
        ]
      ]
     (include-js "js/google-code-prettify/run_prettify.js")
   ]
  )

(defn post [data]
  (base-template
     (:title data)
     [:div.post
       [:h1 (:title data)]
       [:h3 (fdate (:date data))]
       (:html data)]
     (post-footer (:prev data) (:next data) (:tags data))
   )
  )

(defn multiple-posts [title posts]
   (base-template
      title
      (for [post posts]
        (html
          [:h1 (:title post)]
          [:h3 (fdate (:date post))]
          (:html post)
          [:hr]
         )
      )
    )
  )

(defn post-list [title posts]
  (base-template
     title
     [:ul
        (for [{url :file-name text :title} posts] [:li [:a {:href url} text]])
      ]
   )
  )

