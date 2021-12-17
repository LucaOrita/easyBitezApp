$(document).ready(function () {

        if (window.innerWidth > 509) {
            $(".cutii").owlCarousel({
                items: 3,
                center: true,
                nav: true,
                navText: ["<span class='lnr lnr-arrow-left' style='font-weight: 600;color:#ffba00'></span>","<span class='lnr lnr-arrow-right' style='font-weight: 600;color:#ffba00'></span>"],
                dots: true,
                loop: true,
                autoplay: 5000,
                autoplayHoverPause: true,
                responsiveClass: true,
                responsive: {
                    1024: {
                        items: 3
                    },
                    300: {
                        items: 2,
                        center: false,
                    },
                }
            });
        }
    }
);