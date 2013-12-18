(ns smackdown.css
  (:require [garden.core :refer [css]]
            [garden.units :refer [px]])
  )

(defn generate-css []
(css
 [:body
    {:font-family ["\"Open Sans\"" "sans-serif"]};
  ]
 [:.container {:display :block
               :margin [[(px 0) :auto (px 0) :auto]]
               :width (px 800)}]


 )
)
