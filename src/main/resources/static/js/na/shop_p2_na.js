$(document).ready(function () {

    if (window.localStorage.getItem("m2"))
        $("#prima").html(window.localStorage.getItem("m2"));

    $(".label-filter").on('click', function () {
        var label = $(this).attr("type");
        var c = $(".card");
        $(".label-filter").css({"border": "0px solid black"});
        $(this).css({"border": "2px solid black"});
        for (let i = 0; i < c.length; i++) {
            let el = c.eq(i);
            if (el.attr("foodtype") !== label) {
                el.css({"display": "none"});
            } else el.css({"display": "block"});
        }
    });

    $('.card').on('click', function () {

        $(".overlay").css("display", "block");
        $.ajax("/retete/r/" + this.id,
            {
                success: function (data, status, xhr) {
                    $(".content-overlay").empty().append(data);
                }
            });
    });

    $(".addBTN").on('click', function (event) {
        event.stopPropagation();

        var parent = $(this).parent().parent();
        var el = $(this);

        $.ajax("/na/shop/add/mancare/1/" + parent.attr("id"), {
            type: "POST"
        }).done(function () {
            var pp = $(".food-1");
            for (var i = 0; i < pp.length; i++) {
                var el = pp[i];
                if (el.getAttribute("id") === null) {
                    el.style.background = "url('" + parent.children().eq(1).children().eq(0).attr("src") + "')";
                    el.style.backgroundSize = "cover";
                    el.style.backgroundPosition = "center";
                    el.children.item(1).style.display = "block";
                    el.setAttribute("id", parent.attr("id"));

                    var add = document.querySelector("#add" + parent.attr("id"));
                    add.style.opacity = "1";
                    add.style.display = "flex";
                    add.firstElementChild.classList.add('animated');

                    setTimeout(
                        function () {
                            add.style.opacity = "0";
                            setTimeout(
                                function () {
                                    add.style.display = "none";
                                },
                                300
                            );
                            add.firstElementChild.classList.remove('animated');
                        },
                        2000
                    );
                    return false;
                }
            }

        }).fail(function (jqXHR, textStatus, errorThrown) {
            if (jqXHR.status === 409)
                alert("Nu mai ai loc de mancare!");
        });
    });

    $(".x").on('click', function () {
        var parent = $(this).parent();

        $.ajax("/na/shop/remove/mancare/" + parent.attr("id") + "/" + parent.index(), {
            type: "POST"
        }).done(function () {
            parent.css({"background": "#ffba00"}).children().eq(1).css("display", "none");
            parent.removeAttr("id");
        }).fail(function (jqXHR, textStatus, errorThrown) {
        });
    });
});

function openMenu() {
    var menu = $(".menu-wrapper");
    if (menu.css("bottom") === "0px") {
        menu.css("bottom", "-100%");
    } else menu.css("bottom", "0");
}

function next() {
    console.log($(".header-shop").attr("next"));
    $.ajax({
        url: "/na/shop/check/2",
        type: "GET"
    }).done(function () {
        window.localStorage.setItem("m2", $("#prima").html());
        // $("#load").css({"opacity": "1", "z-index": "999999999"});
        location.href = "/na/shop/" + (parseInt($(".header-shop").attr("next")) + 1);
    }).fail(function (jqXHR, textStatus, errorThrown) {
        if (jqXHR.status === 409)
            alert("Nu ai completat tot meniul pentru perioada următoare");
    });
}

function closeReteta() {
    $(".overlay").css("display", "none");
}