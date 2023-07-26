console.log("This is script file")


const toggleSidebar = () => {
    if ($(".sidebar").is(":visible")) {
        $(".sidebar").css("display", "none");
        $(".content").css("margin-left", "0%");
    } else {
        $(".sidebar").css("display", "block");
        $(".content").css("margin-left", "20%");
    }
}


// const search = () => {
//     let query = $("#search-input").val();
//     console.log(query);
//     if (query == '') {
//         $(".search-result").hide();
//     } else {
//         let url = `http://localhost:8080/home/user/search/${query}`
//         fetch(url)
//         .then((respose) => {
//             return respose.json;
//         }).then((data) => {
//             let text = `<div class='list-group'>`;
//             data.forEach((contact) => {
//                 text += `<a href=# class='list-group-item list-group-action'>${contact.name}</a>`
//             });

//             text += `</div>`;
//             $(".search-result").html(text);
//             $(".search-result").show();
//         });

//     }
// }


const search = () => {
    let query = $("#search-input").val();
    console.log(query);
    if (query == '') {
        $(".search-result").hide();
    } else {
        let url = `http://localhost:8080/home/user/search/${query}`;
        fetch(url)
            .then((response) => response.json()) // Parse the response as JSON
            .then((data) => {
                let text = `<div class='list-group'>`;
                data.forEach((contact) => {
                    text += `<a href='/home/user/${contact.cId}/showContact' class='list-group-item list-group-item-action'>${contact.name}</a>`;
                });

                text += `</div>`;
                $(".search-result").html(text);
                $(".search-result").show();
            })
            .catch((error) => {
                console.error("Error fetching data:", error);
            });
    }
}