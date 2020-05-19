import SockJS from 'sockjs-client'
import { Stomp } from '@stomp/stompjs'

var stompClient = null;
const handlers = []

export function connect() {
    const socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, frame => {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/activity', order => {
           handlers.forEach(handler => handler(JSON.parse(order.body)))
        });
    });
}

export function addHandler(handler) {
    handlers.push(handler)
}

export function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

export function sendOrder(order) {
    stompClient.send("/app/update-order", {}, JSON.stringify(order));
}