$(document).ready(function () {
        if (window.localStorage.getItem("p1date"))
            $("#data_day_info").html(window.localStorage.getItem("p1date"));

        if (window.innerWidth > 509) {
            $(".cutii").owlCarousel({
                items: 3,
                slideBy: 3,
                center: true,
                nav: true,
                navText: ["<span class='lnr lnr-arrow-left' style='font-weight: 600;color:#ffba00'></span>", "<span class='lnr lnr-arrow-right' style='font-weight: 600;color:#ffba00'></span>"],
                dots: true,
                loop: true,
                // autoplay: 1000,
                // autoplayHoverPause: true,
                responsiveClass: true,
                responsive: {
                    1024: {
                        items: 3,
                        slideBy: 3
                    },
                    300: {
                        items: 2,
                        slideBy: 2,
                        center: false,
                    },
                }
            });
        }

        $(".item").on('click', function () {

            var div = $(this);
            var POSTdata = {
                "boxID": $(this).attr("data-box-id"),
                "addOn": "on"
            };

            $.ajax({
                url: "/na/shop/select/box",
                type: "POST",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(POSTdata)
            }).done(function () {
                window.localStorage.removeItem("m3");
                window.localStorage.removeItem("m2");
                $(".item").each(function () {
                    $(this).css("opacity", "0.3");
                });
                div.css("opacity", "1");

                var cutii = $('.cutii');
                cutii.trigger('owl.stop');
            }).fail(function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR.status);
                console.log(textStatus);
                console.log(errorThrown);
            })
        });

        $(".info").on('click', function () {
            var el = $(this).parent();

            var POSTdata = {
                "day": $(this).attr("data-day"),
                "date": $(this).attr("data-date")
            };

            $.ajax({
                url: "/na/shop/select/date",
                type: "POST",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(POSTdata)
            }).done(function () {
                window.localStorage.removeItem("m3");
                window.localStorage.removeItem("m2");
                $(".data-m").each(function () {
                    $(this).css("opacity", "0.3");
                });
                el.css("opacity", "1");
            }).fail(function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR.status);
                console.log(textStatus);
                console.log(errorThrown);
            });
        });
    }
);

function next() {
    $.ajax({
        url: "/na/shop/check/1",
        type: "GET"
    }).done(function (data) {
        if (data[0] === null && data[1] === null)
            alert("Nu ai selectat momentul livrării!");
        else if (data[2] === null)
            alert("Nu ai selectat cutia!")
        else {
            window.localStorage.setItem("p1date", $("#data_day_info").html());
            // $("#load").css({"opacity": "1", "z-index": "999999999"});
            location.href = "/na/shop/" + (parseInt($(".header-shop").attr("next")) + 1);
        }
    });
}