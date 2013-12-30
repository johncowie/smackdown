(ns smackdown.wordpress
  (:require [clj-http.client :as client]
            [cheshire.core :refer [parse-string]]
            [clj-time.format :refer [parse]]
            ))

(defn- get-data [url]
  (parse-string (:body (client/get url)) #(keyword %)))

(defn- translate-post [p]
  {
   :title (:title p)
   :tags (set (map :title (:tags p)))
   :date (parse (:date p))
   :file-name (str (:slug p) ".html")
   :html (:content p)
   }
  )

(defn get-wp-posts-data []
  (let [json (get-data "http://johncowie.co.uk?json=1")]
     (map translate-post (:posts json))
    )
  )

(hiccup.util/to-str (:html (first (get-wp-posts-data))))

