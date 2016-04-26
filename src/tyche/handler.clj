(ns tyche.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s])
  (:import java.util.Date))

(s/defschema Stock
  {:name s/Str
   (s/optional-key :description) s/Str
   :symbol s/Str
   :price s/Num
   :date (s/maybe Date)})

(def app
  (api
    {:swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "Tyche"
                    :description "Compojure Api example"}
             :tags [{:name "api", :description "some apis"}]}}}

    (context "/api" []
      :tags ["api"]

      (GET "/plus" []
        :return {:result Long}
        :query-params [x :- Long, y :- Long]
        :summary "adds two numbers together"
        (ok {:result (+ x y)}))

      (POST "/echo" []
        :return Stock
        :body [stock Stock]
        :summary "echoes a Stock"
        (ok stock)))))
