(ns chunnel.core
  (:require [clojure.core.async :as async]
            [clojure.core.server :as server]))

(async/chan)

(comment
  ; sockets and socket servers can push and pull
  ; channel
  ;  push
  ;  pull
  ; by default we start a single socket server


  ; channels are bidirectional, you create an object
  ; pass it around, and anything that touches it can
  ; push or pull from it
  ; an async socket is also bi directional, but to
  ; create the object you must define a few extra things:
  ; - server
  ; - [host]
  ; - [port]
  ; this information will be used to establish the socket connection,
  ; and thus where to push and pull information from.
  ; options for input are as follows:
  ; - host, port - creates a socketclietn
  )

(defn chan
  "Creates a channel with an optional buffer, an optional transducer
  (like (map f), (filter p) etc or a composition thereof), and an
  optional exception-handler. If buf-or-n is a number, will create
  and use a fixed buffer of that size. If a transducer is supplied a
  buffer must be specified. ex-handler must be a fn of one argument -
  if an exception occurs during transformation it will be called with
  the Throwable as an argument, and any non-nil return value will be
  placed in the channel."
  [buf-or-n xform ex-handler]
  (let [intermediate-chan (async/chan buf-or-n xform ex-handler)]
    (async/thread
      (doseq [to-send (async/chan)]))
    intermediate-chan))
