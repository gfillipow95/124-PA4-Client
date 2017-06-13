function getPlaceAndTax(zipcode){
  getPlace(zipcode);
  //getTax(zipcode);
}

function getPlace(zipcode){
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200){
            var result = xhr.responseText;
            zipcodeError = document.querySelector("#zipError");
            if(result == ",,,"){
                zipcodeError.innerHTML = "Please enter a valid zip code";
            }else{
                zipcodeError.innerHTML = "";
                var place = result.split (',');

                var tax = place[2];

                var totalPrice = getTotalPriceWithTax(tax);

                document.querySelector("#city").value = place[0];
                document.querySelector("#state").value = place[1];
                document.getElementById("totalCost").innerHTML = "<strong>Total Cost: " + totalPrice + "</strong>";
            }
        }
    }
    xhr.open("GET","getCityState.php?zip=" + zipcode, true);
    xhr.send();
}

function getTotalPriceWithTax(taxRate){
    var total = document.querySelector("#totalCost");
    var totalData = total.getAttribute("data-total");
    if(totalData == '0'){
        var productPrice = document.querySelector("#price");
        var priceData = parseFloat(productPrice.getAttribute("data-price"));
    }else{
        var priceData = parseFloat(totalData);
    }


    var taxPrice = priceData * parseFloat(taxRate);
    var newTotal = parseFloat(taxPrice) + priceData;

    document.querySelector("#totalCost").setAttribute("data-total", newTotal.toString());
    document.querySelector("#totalCostInput").value = newTotal.toFixed(2);
    return newTotal.toFixed(2);
}

function addQuantity(){
    var total = document.querySelector("#totalCost");
    var totalData = total.getAttribute("data-total");
    if(totalData == '0'){
        var productPrice = document.querySelector("#price");
        var priceData = parseFloat(productPrice.getAttribute("data-price"));
    }else{
        var priceData = parseFloat(totalData).toFixed(2);
    }

    var q = document.querySelector("#quantity");
    var selectQuantity = q.options[q.selectedIndex].text;

    var newTotal = parseInt(selectQuantity) * priceData;

    document.querySelector("#totalCost").setAttribute("data-total", newTotal.toString());
    document.querySelector("#totalCost").innerHTML = "<strong>Total Cost: " + newTotal + "</strong>";
    document.querySelector("#totalCostInput").value = newTotal.toFixed(2);
}

function addShipping(){
    var total = document.querySelector("#totalCost");
    var totalData = total.getAttribute("data-total");
    if(totalData == '0'){
        var productPrice = document.querySelector("#price");
        var priceData = parseFloat(productPrice.getAttribute("data-price"));
    }else{
        var priceData = parseFloat(totalData).toFixed(2);
    }

    var q = document.querySelector("#shippingMethod");
    var selectQuantity = q.options[q.selectedIndex].value;

    var newTotal = parseFloat(selectQuantity) + parseFloat(priceData);

    document.querySelector("#totalCost").setAttribute("data-total", newTotal.toString());
    document.querySelector("#totalCost").innerHTML = "<strong>Total Cost: " + newTotal + "</strong>";
    document.querySelector("#totalCostInput").value = newTotal.toFixed(2);
}
