var homeBtn = document.querySelector("#navbar-home");
var form  = document.getElementsByTagName('form')[0];


var firstName = document.querySelector("#firstname");
var lastName = document.querySelector("#lastname");
var email = document.querySelector("#email");
var phone = document.querySelector("#phonenumber");
var quantity = document.querySelector("#quantity");
var address = document.querySelector("#address");
var city = document.querySelector("#city");
var state = document.querySelector("#state");
var zipcode = document.querySelector("#zipcode");
var country = document.querySelector("#country");
var creditcard = document.querySelector("#cardnumber");
var cvcNumber = document.querySelector("#cvc");
var expirationDate = document.querySelector("#expdate");

var firstNameError = document.querySelector("#firstNameError");
var lastNameError = document.querySelector("#lastNameError");
var emailError = document.querySelector("#emailError");
var phoneError = document.querySelector("#phoneError");
var quantityError = document.querySelector("#quantityError");
var addressError = document.querySelector("#addressError");
var cityError = document.querySelector("#cityError");
var stateError = document.querySelector("#stateError");
var zipcodeError = document.querySelector("#zipError");
var countryError = document.querySelector("#countryError");
var cardError = document.querySelector("#cardError");
var cvcError = document.querySelector("#cvcError");
var expError = document.querySelector("#expError");
var error = document.querySelector('.error');

homeBtn.addEventListener("click", function(e){
    window.location.href = "../../index.html";
});

function validPhoneNumber(phoneNum){
    return phoneNum.match(/^(\([0-9]{3}\) |[0-9]{3}-)[0-9]{3}-[0-9]{4}/);
}

function validStreetAddress(streetAddress){
    return streetAddress.match(/^\s*\S+(?:\s+\S+){2}/);
}

function validCity(cityName){
    return cityName.match(/^[a-zA-Z]+(?:[\s-][a-zA-Z]+)*$/);
}

function validState(stateName){
    return stateName.match(/^\S+([^0-9]*)$/);
}

function validZipcode(zipcodeNum){
    return zipcodeNum.match(/^\d{5}(?:[-\s]\d{4})?$/);
}

function validCountry(countryName){
    return countryName.match(/^\S+([^0-9]*)$/);
}

function validCreditCard(cardNum){
    //regex patterns for mastercard, visa, and amex
    return cardNum.match(/(5[1-5]\d{14})$|(4\d{12}(\d{3})?)$|(3[47]\d{13})$/);
}

function validCVC(cvcNum){
    return cvcNum.match(/^[0-9]{3,4}$/);
}

function validExpDate(expDate){
    var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
    var year = currentTime.getFullYear().toString().substr(-2);
    var inputMonth = expDate.split("/")[0];
    if(inputMonth[0] === "0"){
        inputMonth = inputMonth[1];
    }
    var inputYear = expDate.split("/")[1];
    if(expDate.match(/^(0[1-9]|1[0-2])\/([0-9]{2})$/)){
        if(inputYear < year){
            return false;
        }else if(inputMonth <= month && inputYear === year){
            return false;
        }else{
            return true;
        }
    }else{
        return false;
    }
}

function zoomIn() {
    var img = document.querySelector(".product-page-image");
    img.style.transform = "scale(1.5)";
}

function zoomOut() {
    var img = document.querySelector(".product-page-image");
    img.style.transform = "scale(1)";
}

// firstName.addEventListener("keyup", function(event){
//     if(firstName.validity.valid){
//         firstNameError.innerHTML="";
//     }
// });
//
// lastName.addEventListener("keyup", function(event){
//     if(lastName.validity.valid){
//         lastNameError.innerHTML="";
//     }
// });
//
// email.addEventListener("keyup", function(event){
//     if(email.validity.valid){
//         emailError.innerHTML="";
//     }
// });
//
// phone.addEventListener("keyup", function(event){
//     if(phone.validity.valid && validPhoneNumber(phone.value)){
//         phoneError.innerHTML="";
//     }
// });
//
// address.addEventListener("keyup", function(event){
//     if(address.validity.valid && validStreetAddress(address.value)){
//         addressError.innerHTML="";
//     }
// });
//
// city.addEventListener("keyup", function(event){
//     if(city.validity.valid && validCity(city.value)){
//         cityError.innerHTML="";
//     }
// });
//
// state.addEventListener("keyup", function(event){
//     if(state.validity.valid && validState(state.value)){
//         stateError.innerHTML="";
//     }
// });
//
// zipcode.addEventListener("keyup", function(event){
//    if(state.validity.valid && validZipcode(zipcode.value)) {
//        zipcodeError.innerHTML="";
//    }
// });
//
// country.addEventListener("keyup", function(event){
//     if(country.validity.valid && validCountry(country.value)){
//         countryError.innerHTML="";
//     }
// });
//
// creditcard.addEventListener("keyup", function(event){
//     if(creditcard.validity.valid && validCreditCard(creditcard.value)){
//         cardError.innerHTML="";
//     }
// });
//
// cvcNumber.addEventListener("keyup", function(event){
//     if(cvcNumber.validity.valid && validCVC(cvcNumber.value)){
//         cvcError.innerHTML="";
//     }
// });
//
// expirationDate.addEventListener("keyup", function(event){
//     if(expirationDate.validity.valid && validExpDate(expirationDate.value)){
//         expError.innerHTML="";
//     }
// });
//
// form.addEventListener("submit", function(event){
//     if(!firstName.validity.valid || firstName.value === ""){
//         firstNameError.innerHTML = "Please enter your first name";
//         event.preventDefault();
//     }
//     if(!lastName.validity.valid || lastName.value === ""){
//         lastNameError.innerHTML = "Please enter your last name";
//         event.preventDefault();
//     }
//     if(!email.validity.valid || email.value === ""){
//         emailError.innerHTML = "Please enter a valid email address";
//         event.preventDefault();
//     }
//     if(!phone.validity.valid || phone.value === "" || !validPhoneNumber(phone.value)){
//         phoneError.innerHTML = "Please enter a valid phone number";
//         event.preventDefault();
//     }
//     if(!address.validity.valid || address.value === "" || !validStreetAddress(address.value)){
//         addressError.innerHTML = "Please enter a valid street address";
//         event.preventDefault();
//     }
//     if(!city.validity.valid || city.value === "" || !validCity(city.value)){
//         cityError.innerHTML = "Please enter a valid city";
//         event.preventDefault();
//     }
//     if(!state.validity.valid || state.value === "" || !validState(state.value)){
//         stateError.innerHTML = "Please enter a valid state/province";
//         event.preventDefault();
//     }
//     if(!zipcode.validity.valid || zipcode.value === "" || !validZipcode(zipcode.value)){
//         zipcodeError.innerHTML = "Please enter a valid zip code";
//         event.preventDefault();
//     }
//     if(!country.validity.valid || country.value === "" || !validCountry(country.value)){
//         countryError.innerHTML = "Please enter a valid country";
//         event.preventDefault();
//     }
//     if(!creditcard.validity.valid || creditcard.value === "" || !validCreditCard(creditcard.value)){
//         cardError.innerHTML = "Please enter a valid credit card number";
//         event.preventDefault();
//     }
//     if(!cvcNumber.validity.valid || cvcNumber.value === "" || !validCVC(cvcNumber.value)){
//         cvcError.innerHTML = "Please enter a valid cvc number";
//         event.preventDefault();
//     }
//     if(!expirationDate.validity.valid || expirationDate.value === "" || !validExpDate(expirationDate.value)){
//         expError.innerHTML = "Please enter a valid expiration date";
//         event.preventDefault();
//     }
// });
