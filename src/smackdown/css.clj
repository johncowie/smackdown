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
          :margin (px 5)}]
    [:a  {:color (:header-text palette)}
       [:&.selected
         {:color :#ff0000}
        ]
     ]
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
     :margin (px 0)
     :padding (px 5)
     :text-align :center
     }
    (at-media {:max-width width}
              [:& {:font-size (px 50)}])
  ]
 [:.content
    {:padding (px 10)
     :line-height 1.7}
  ]
 [:.footer
    {:padding (px 10)}
    [:.next {:float :right}]
    [:.prev {:float :left}]
  ]

 [:.clear-fix {:clear :both}]


 )
)
