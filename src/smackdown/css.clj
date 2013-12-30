(ns smackdown.css
  (:require [garden.core :refer [css]]
            [garden.units :refer [px pc]]
            [garden.stylesheet :refer [at-media]]
            )
  )

(defn generate-css []
(css
 [:.footer
    [:.next {:float :left}]
    [:.prev {:float :right}]
  ]
  [:.post-header
    {:margin-bottom (px 20)}
    [:h1 {:margin-bottom (px 0)}]
    [:.post-date {:color :#999999}]
   ]
 )
)
