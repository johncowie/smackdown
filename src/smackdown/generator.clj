(ns smackdown.generator
  (:require [smackdown.views :as views :refer [post post-list multiple-posts]]
            [smackdown.util :refer [wipe-directory! nth-safe spit-rec]]
            [smackdown.md :refer [get-posts-data]]
            [smackdown.wordpress :refer [get-wp-posts-data]]
            )
  (:gen-class))

(defn- define-page [rel-path data]
  {:rel-path rel-path :data data})

(defn- generate-index-page [files-metadata]
     [{:rel-path "index.html"
      :data (views/post-list "Index Page" files-metadata)}])

(defn- link-posts [f]
  (let [files-metadata (reverse (sort-by :date f))]
  (loop [r [] i 0]
    (if (= i (count files-metadata))
      r
      (let [curr (nth files-metadata i)]
      (recur (concat r [
          (let [prev (nth-safe files-metadata (inc i))
                next (nth-safe files-metadata (dec i))]
            (merge curr {:next (if next {:url (:file-name next)})
                         :prev (if prev {:url (:file-name prev)})}))])
             (inc i)))))))

(defn- postprocess [files-metadata]
  (link-posts files-metadata))

(defn- get-file-tag-groups [files-metadata]
  (group-by :tag-group (flatten (for [f files-metadata] (map #(assoc f :tag-group %) (:tags f)))))
  )

(defn generate-tag-files [files-metadata]
  (for [[k v] (get-file-tag-groups files-metadata)]
     (define-page (str k ".html") (views/multiple-posts k v))))

(defn- generate-files-html [files-metadata]
  (for [f files-metadata] (define-page (:file-name f) (views/post f))))

(defn- generate-all-posts-html [file-metadata]
    [(define-page "all.html" (views/multiple-posts "All posts" file-metadata))])

(defn generate-all [files-metadata]
  (apply concat (map #(% files-metadata)
       [generate-index-page
        generate-tag-files
        generate-files-html
        generate-all-posts-html])))

(defn generate-site! [markdown-dir target-dir]
    (let [fs (generate-all (postprocess (get-posts-data markdown-dir)))]
      (wipe-directory! target-dir)
      (doseq [{rel-path :rel-path data :data} fs]
        (spit-rec (str target-dir "/" rel-path) data))))

(defn -main [& args]
  (println "Generating site...")
  (generate-site! "markdown" (first args))
  (println "Generated site to " (first args)))






