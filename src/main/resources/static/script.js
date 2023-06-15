  var typed = new Typed("#auto-type", {
    strings: ["The easiest","The fastest","The smoothest","The leading","The simplest", "The quickest and safest"],
    typeSpeed: 150,
    backSpeed: 150,
    loop: true,
  })
function addToCart(value) {
    var expires = "";

    var date = new Date();
    date.setTime(date.getTime() + (52460601000));
    expires = "; expires=" + date.toUTCString();

    // "product=1; expires = date; path=/"
    // "product=1,2,3,4; expires = date; path=/"

    var productValues = getCookieContent();

    if (productValues === null) {
        document.cookie = "product=" + (value || "") + expires + "; path=/";
    } else {
        document.cookie = "product=" + productValuesListString + "," + value + expires + "; path=/";
    }

}
function getCookieContent() {
    try {
        var cookieParameters = document.cookie.split(';');
        var productInfo = cookieParameters[0];
        var productValuesListString = productInfo.split("=")[1];
        return productValuesListString;
    } catch(e) {
        return null; // no cookies created yet
    }
}
function eraseCookie(name) {
    document.cookie = name +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}