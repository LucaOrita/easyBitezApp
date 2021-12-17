$(document).ready(function () {

    if (getCookie("user") !== "") {
        $("#cookie").css("display", "none");
    }

    $(".gdpr-ok").on("click", function () {
        $("#cookie").css("display", "none");
        setCookie("user", "accepted", 20);
    });

    $(".gdpr-no-new").on("click", function () {
        $("#cookie").css("display", "none");
    });


    $(".review").owlCarousel({
        items: 3,
        slideBy: 3,
        center: true,
        nav: true,
        navText: ["<span class='lnr lnr-arrow-left' style='font-weight: 600;color:#ffba00'></span>","<span class='lnr lnr-arrow-right' style='font-weight: 600;color:#ffba00'></span>"],
        dots: true,
        loop: true,
        autoplay: 5000,
        autoplayHoverPause: true,
        responsiveClass: true,
        responsive: {
            1500: {
                items: 3,
                slideBy: 3
            },
            500: {
                items: 2,
                slideBy: 2
            },
            300: {
                items: 1,
                slideBy: 1
            },
        }
    });

});

const setCookie = (cname, cvalue, exdays) => {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

// carousel

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}