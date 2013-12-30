(ns smackdown.css
  (:require [garden.core :refer [css]]
            [garden.units :refer [px pc]]
            [garden.stylesheet :refer [at-media]]
            )
  )

(def width (px 600))

(defn make-palette [header-bg header-text post-bg post-text links]
  {:header-bg header-bg
   :header-text header-text
   :post-bg post-bg
   :post-text post-text
   :links links}
  )

(def palettes {
:a (make-palette :#BC294E :#26103D :#C7C1C2 :#3D2B5C :#912049)
:b (make-palette :#036564 :#E8DDCB :#E8DDCB :#033649 :#031634)
:c (make-palette :#555555 :#ffffff :#ffffff :#000000 :#ff0000)
})

(def palette (:c palettes))

(defn generate-css []
(css

 [:.footer
    {:padding (px 10)}
    [:.next {:float :right}]
    [:.prev {:float :left}]
  ]

 [:.clear-fix {:clear :both}]


 )
)
