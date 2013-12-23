(ns smackdown.md
  (:require [smackdown.util :refer [all-files nth-safe]]
            [clj-time.format :refer [formatter parse]]
            [me.raynes.cegdown :refer (to-html)]
            ))

(def custom-formatter (formatter "dd/MM/yyyy"))

(defn- parse-date [s] (parse custom-formatter s))

(defn- generate-file-name [title]
  (.toLowerCase (str (clojure.string/replace title #"\W" "_") ".html")))

(defn- get-file-metadata [file]
  (let [lines (line-seq (clojure.java.io/reader file))
        title    (nth lines 0)
        date     (nth lines 1)
        tags     (nth lines 2)
        ]
    {:title title
     :tags (set (clojure.string/split tags #"\W+"))
     :date  (parse-date date)
     :file-name (generate-file-name title)
     :html (to-html (apply str (interpose "\n" (drop 4 lines))))}))

(defn get-posts-data [source-dir]
  (for [file (all-files source-dir)] (get-file-metadata file)))
