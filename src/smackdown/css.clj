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

(def pa (make-palette :#BC294E :#26103D :#C7C1C2 :#3D2B5C :#912049))
(def pb (make-palette :#036564 :#E8DDCB :#E8DDCB :#033649 :#031634))

(def palette pb)

(defn generate-css []
(css
 [:body
    {:font-family ["\"Open Sans\"" "sans-serif"]
     :font-size (px 18)
     :background-color (:post-bg palette)
     :color (:post-text palette)
     :padding (px 0)
     :margin (px 0)}
  ]
 [:a
    {:color (:links palette)
     :text-decoration :none}
  ]
 [:code
    {:display :block
     :background-color :white
     :margin [[(px 10) (px 0)]]
     :padding (px 10)}
  ]
 [:.nav
    {
     :display :block
     :width "100%"
     :background-color (:post-text palette)
     }
    [:ul {:margin 0 :padding 0}]
    [:li {:display :inline-block
          :margin (px 5)
          :color (:header-text palette)}]
  ]
 [:.container {:display :block
               :margin [[(px 0) :auto (px 0) :auto]]
               :width width}
   (at-media {:max-width width}
           [:& {:width "100%"}])]

 [:.header
    {:display :block
     :background-color (:header-bg palette)
     :color (:header-text palette)
     :font-size (px 80)
     :font-weight 700
     :margin (px 0)
     }
    (at-media {:max-width width}
              [:& {:font-size (px 50)}])
  ]
 [:.post
    {:height "100%"
     :padding (px 10)}
  ]
 [:.footer
    {:padding (px 10)}
    [:.next {:float :right}]
    [:.prev {:float :left}]
  ]

 [:.clear-fix {:clear :both}]


 )
)
