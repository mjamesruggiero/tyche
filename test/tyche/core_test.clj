(ns tyche.core-test
  (:require [cheshire.core :as cheshire]
            [clojure.test :refer :all]
            [tyche.handler :refer :all]
            [ring.mock.request :as mock]))

(defn parse-body [body]
  (cheshire/parse-string (slurp body) true))

(deftest get-test
  (testing "Test GET request to /hello?name={a-name} returns expected response"
    (let [response (app (-> (mock/request :get  "/api/plus?x=1&y=2")))
          body     (parse-body (:body response))]
      (is (= (:status response) 200))
      (is (= (:result body) 3)))))

(def fake-stock {:name  "Google, Inc.",
                 :description  "This is a stock",
                 :symbol  "GOOG",
                 :price 100.00,
                 :date "2016-04-27T04:31:13.172Z"})

(deftest echo-test
  (testing "Test POST request to /echo returns expected stock"
    (let [rqst (mock/content-type (mock/body
                                    (mock/request :post "/api/echo")
                                    (cheshire/generate-string fake-stock))
                                  "application/json")
          body (parse-body (:body (app (-> rqst))))]
      (is (= (:name body) "Google, Inc."))
      (is (= (:symbol body) "GOOG"))
      (is (= (:description body) "This is a stock"))
      (is (= (:price body) 100.00)))))
