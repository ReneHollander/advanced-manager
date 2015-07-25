print("start");

setTimeout(function () {
    print("after 1 second");
}, 1000);

setInterval(function () {
    print("every second");
}, 1000);