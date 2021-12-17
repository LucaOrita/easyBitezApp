$(document).ready(function () {

    $('.card').on('click', function () {

        $(".overlay").css("display", "block");
        $.ajax("/retete/r/" + this.id,
            {
                success: function (data, status, xhr) {
                    $(".content-overlay").empty().append(data);
                }
            });
    });

});

function gustari() {

    document.querySelectorAll(".controller-filt.currentFilt")[0].classList.remove("currentFilt");
    document.querySelectorAll(".controller-filt")[1].classList.add("currentFilt");

    var categ = document.querySelectorAll(".controller-sub-filt");
    for (item = 0; item < categ.length; item++) {
        categ[item].style.display = "none";
    }

    var categ = document.querySelectorAll(".controller-sub-filt.g");
    for (item = 0; item < categ.length; item++) {
        categ[item].style.display = "block";
    }
}

function mancare() {

    document.querySelectorAll(".controller-filt.currentFilt")[0].classList.remove("currentFilt");
    document.querySelectorAll(".controller-filt")[0].classList.add("currentFilt");

    var categ = document.querySelectorAll(".controller-sub-filt");
    for (item = 0; item < categ.length; item++) {
        categ[item].style.display = "none";
        categ[item].style.color = "rgb(119, 119, 119)";
    }

    var categ = document.querySelectorAll(".controller-sub-filt.m");
    for (item = 0; item < categ.length; item++) {
        categ[item].style.display = "block";
    }
}

function micdejun() {

    document.querySelectorAll(".controller-filt.currentFilt")[0].classList.remove("currentFilt");
    document.querySelectorAll(".controller-filt")[2].classList.add("currentFilt");

    var categ = document.querySelectorAll(".controller-sub-filt");
    for (item = 0; item < categ.length; item++) {
        categ[item].style.display = "none";
    }

    var categ = document.querySelectorAll(".controller-sub-filt.mi");
    for (item = 0; item < categ.length; item++) {
        categ[item].style.display = "block";
    }
}

function closeReteta() {
    $(".overlay").css("display", "none");
}

$(document).keyup(function (e) {
    if (e.key === "Escape") {
        $(".overlay").css("display", "none");
    }
});