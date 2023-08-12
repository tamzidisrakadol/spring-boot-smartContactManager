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


const paymentStart=()=>{
    console.log("payment Started")
    let amount = $("#payment_field").val()
    console.log(amount)   
    if(amount=="" || amount==null){
        alert("Amount is required")
        return
    }

    $.ajax({
        url : "/home/user/create_order",
        data:JSON.stringify({amount:amount,info:'order_request'}),
        contentType:'application/json',
        type:'POST',
        dataType:'json',
        success:function(response){
            console.log(response)
            if(response.status == "created"){
                let options={
                    key:'rzp_test_ikSxuKRBygmGpy',
                    amount:response.amount,
                    currency:"INR",
                    name:"AD TECH",
                    description:"Donation",
                    image:"",
                    order_id:response.order_id,
                    handler:function(response){
                        console.log(response.razorpay_payment_id)
                        console.log(response.razorpay_order_id)
                        console.log(response.razorpay_signature)
                        console.log("Successfully")
                    },
                    prefill:{
                        "name": "",
                        "email": "",
                        "contact": ""
                        },
                        theme: {
                            "color": "#3399cc"
                            }

                }
                var rzp1 = new Razorpay(options)
                rzp1.on('payment.failed', function (response){
    console.log(response.error.code);
                    console.log(response.error.description);
                    console.log(response.error.source);
                    console.log(response.error.step);
                    console.log(response.error.reason);
                    console.log(response.error.metadata.order_id);
                    console.log(response.error.metadata.payment_id);
                    })
            }
        },
       
    })
    
}

