$(document).ready(function () {

    if(window.localStorage.getItem("code"))
        $("#feedback").html(window.localStorage.getItem("code"));

    $("form").submit(function (event) {
        var formData = $(this).serialize();
        $.ajax({
            type: "POST",
            url: "/shop/code",
            data: formData
        }).done(function (data) {
            var feedback = $("#feedback");
            var err = $("#err_feedback");
            if (data === "-3.0") {
                err.html("Codul nu se potrivește cu ceea ce ai selectat!");
                setTimeout(function () {
                    err.html("")
                }, 3000);
            } else if (data === "-2.0") {
                err.html("Ai aplicat deja un cod de reducere!");
                setTimeout(function () {
                    err.html("")
                }, 3000);
            } else if (data === "-1.0") {
                err.html("Cuponul nu există!").css({"color": "red !important"});
                setTimeout(function () {
                    err.html("")
                }, 3000);
            } else {
                err.html("");
                console.log(data[0]);
                if (data[0] >= '0' && data[0] <= '9') {
                    feedback.html("<span>Prețul dumneavoastră este: " + data + " lei</span><span class='deleteCode'>&times;</span>");
                    $("#ammout-buy").html("").html(data);
                } else feedback.html("<span>" + data + "</span><span class='deleteCode'>&times;</span>");

                window.localStorage.setItem("code", " " + feedback.html());
            }
        });
        event.preventDefault();
    });

    $("#feedback").on('click', '.deleteCode' , function(){
        console.log("request...");
        $.ajax({
            type: "POST",
            url: "/shop/delete/code"
        }).done(function (data) {
            window.localStorage.removeItem("code");
            $("#feedback").html("");
            $("#ammout-buy").html("").html(data);
        });
    });

    $("#cbx-detalii").change(function () {
        if (this.checked) {
            $("#text-on-toggle").html = "Date de facturare și livrare";
            $("#facturare").css("display", "none");
            $(".order").css("display", "block");
        } else {
            $("#text-on-toggle").html = "Date de livrare";
            $("#facturare").css("display", "block");
            $(".order").css("display", "grid");
        }
    });


    $(".next-btn--, #buy-goods").on('click', function () {

        var livrare = {
            "nume": document.querySelector("#nume").innerHTML.trim().replaceAll("'", "").replaceAll('"', ""),
            "nrTel": document.querySelector("#nrTel").innerHTML.trim().replaceAll("'", "").replaceAll('"', ""),
            "town": document.querySelector('#town').innerHTML.trim().replaceAll("'", "").replaceAll('"', ""),
            "judet": document.querySelector('#judet').innerHTML.trim().replaceAll("'", "").replaceAll('"', ""),
            'strada': document.querySelector('#strada').innerHTML.trim().replaceAll("'", "").replaceAll('"', ""),
            'nrStrada': document.querySelector('#nrStrada').innerHTML.trim().replaceAll("'", "").replaceAll('"', ""),
            'detalii': document.querySelector('#detaliiLiv').innerHTML.trim().replaceAll("'", "").replaceAll('"', "")
        };

        var facturare = {
            'nume2': document.querySelector("#nume2").innerHTML.trim().replaceAll("'", "").replaceAll('"', ""),
            'nrTel2': document.querySelector("#nrTel2").innerHTML.trim().replaceAll("'", "").replaceAll('"', ""),
            'town2': document.querySelector('#town2').innerHTML.trim().replaceAll("'", "").replaceAll('"', ""),
            'judet2': document.querySelector('#judet2').innerHTML.trim().replaceAll("'", "").replaceAll('"', ""),
            'strada2': document.querySelector('#strada2').innerHTML.trim().replaceAll("'", "").replaceAll('"', ""),
            'nrStrada2': document.querySelector('#nrStrada2').innerHTML.trim().replaceAll("'", "").replaceAll('"', ""),
            'detalii2': document.querySelector('#detaliiLiv2').innerHTML.trim().replaceAll("'", "").replaceAll('"', "")
        };

        let ok = true;

        if (livrare.nume === "<br>" || livrare.nume === "<br>" || livrare.nrTel === "<br>" || livrare.judet === "<br>" || livrare.strada === "<br>" || livrare.nrStrada === "<br>" || livrare.detalii === "<br>" || livrare.nrTel === "" || livrare.judet === "" || livrare.strada === "" || livrare.nrStrada === "" || livrare.detalii === "") {
            ok = false;
            alert("Nu ai completat datele de livrare");
        }

        if (!$("#cbx-detalii").is(":checked") && ok)
            if (facturare.nume2 === "<br>" || facturare.nume2 === "<br>" || facturare.nrTel2 === "<br>" || facturare.judet2 === "<br>" || facturare.strada2 === "<br>" || facturare.nrStrada2 === "<br>" || facturare.detalii2 === "<br>" || facturare.nrTel2 === "" || facturare.judet2 === "" || facturare.strada2 === "" || facturare.nrStrada2 === "" || facturare.detalii2 === "") {
                ok = false;
                alert("Nu ai completat datele de facturare");
            }
        if (ok) {
            if (document.getElementById("cbx").checked) {
                console.log(livrare);
                console.log(facturare);
                var POSTdata = JSON.stringify($.extend(livrare, facturare));
                $("#load").css({"opacity": "1", "z-index": "999999999"});
                $.ajax({
                    type: "POST",
                    url: "/shop/getReady",
                    contentType: 'application/json; charset=utf-8',
                    data: POSTdata
                }).done(function () {
                    window.localStorage.removeItem("m2");
                    window.localStorage.removeItem("m3");
                    window.localStorage.removeItem("p1date");
                    location.href = "/shop/pay";
                }).fail(function (jqXHR, textStatus, errorThrown) {
                    if (jqXHR.status === 400)
                        alert("Nu ai nicio rețetă in cos");
                });
            } else alert("Trebuie să fii de acord cu termenii și condițiile!");
        }
    });
});