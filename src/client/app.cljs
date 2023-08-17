(ns client.app
  (:require [reagent.core :as r]
            [ajax.core :refer [POST]]))

(def initial-state
  {:str1 ""
   :str2 ""})

(defonce state
  (r/atom initial-state))

(defn change-fn [kw]
  (fn [e]
    (swap! state assoc kw
           (.. e -target -value))))

(defn better-name [data]
  (reset! state
          (assoc initial-state :result (:result data))))

(defn app []
  (let [{:keys [str1 str2 loading result]} @state]
    [:main {:class "flex justify-center items-center h-screen bg-gray-200"}
     [:div {:class "bg-white mx-auto md:shadow-md md:rounded px-8 py-6 w-full md:w-1/2 border-t-4 border-indigo-500"}
      [:h1 {:class "mb-4 text-2xl font-bold text-indigo-500"} "Flexiana Take Home Assignment"]
      [:p {:class "mb-4"}
       "Check if a portion of the first string can be rearranged to produce the second string. Only lower case letters will be used (a-z). No punctuation or digits will be included."]
      [:form {:class ""
              :on-submit #(do
                            (.preventDefault %)
                            (swap! state assoc :loading true)
                            (POST
                              "http://localhost:8080/api/scramble"
                              {:params (select-keys @state [:str1 :str2])
                               :handler better-name}))}
       [:input {:class "shadow border rounded w-full py-2 px-3 mb-2 outline-indigo-500"
                :placeholder "String 1"
                :value str1
                :on-change (change-fn :str1)}]
       [:input {:class "shadow border rounded w-full py-2 px-3 mb-2 outline-indigo-500"
                :placeholder "String 2"
                :value str2
                :on-change (change-fn :str2)}]
       [:button {:class "bg-indigo-500 hover:bg-indigo-700 text-white font-bold py-2 px-4 rounded shadow"
                 :type "submit"}
        "Submit"]]
      (when loading
        [:p {:class "mt-6 text-center"}
         "..."])
      (when-not (nil? result)
        [:p {:class "mt-6"}
         (if result
           "The first string has a portion of characters that can be rearranged to be the second string."
           "The second string cannot be formed by any combination of the characters in the first string.")])]]))
