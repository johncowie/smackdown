(ns smackdown.util)

(defn nth-safe [c i]
  (if (or (< i 0) (>= i (count c)))
    nil
    (nth c i)))

(defn select-vals [m ks] (map m ks))

(defn apply-vals [f m ks] (apply f (select-vals m ks)))

(defn all-files [loc] (filter #(not (.isDirectory %)) (file-seq (clojure.java.io/file loc))))

(defn wipe-directory!   [dir]
  (doseq [f (rest (file-seq (clojure.java.io/file dir)))]
    (.delete f)))

(defn create-dir [loc]
  (if-not (nil? loc)
    (let [dir (clojure.java.io/file loc)]
      (if-not (.exists dir)
        (do
          (create-dir (.getParent dir))
          (.mkdir dir))))))

(defn spit-rec [f d]
  (do
  (create-dir (.getParent (clojure.java.io/file f)))
  (spit f d)))
