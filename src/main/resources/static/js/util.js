function goto(location){
    window.location=location
}
function loadInfo(){
     var loadInfo = document.getElementById("LoadInfo");
     var loadFileFrom = document.getElementById("LoadFileForm");
    loadInfo.hidden=false;
    loadFileFrom.hidden=true;
    loadFileFrom.submit();
}

function checkSecid(){
    var elem = document.getElementById("secid");
    var re = /^[a-zA-Z0-9]{4,18}$/;
    return re.test(elem.value);
}

function checkRegnumber(){
    var elem = document.getElementById("regnumber");
    var re = /^\d-\d{2}-\d{5}-[A-Z]$/;
    return re.test(elem.value);
}

function checkName(elem){
    var re = /^["-?!,.а-яА-ЯёЁ0-9\sa-zA-Z()]{3,}$/;
    return re.test(elem.value);
}

function checkTradedate(elem){
    var re = /^\d\d\d\d-\d\d-\d\d$/;
    return re.test(elem.value);
}

function checkNumtrades(elem){
    var re = /^[0-9]{1,}.{0,1}[0-9]{0,}$/;
    return re.test(elem.value);
}

function checkOpen(elem){
    var re = /^[0-9]+$/;
    return re.test(elem.value);
}

function checkClose(elem){
    var re = /^[0-9]+$/;
    return re.test(elem.value);
}

function validateSecuritiesForm(){
    var secidError = document.getElementById("secidError");
    var regnumberError = document.getElementById("regnumberError");
    var nameError = document.getElementById("nameError");
    var emitentTitleError = document.getElementById("emitentTitleError");

    secidError.hidden = true;
    regnumberError.hidden = true;
    nameError.hidden = true;
    emitentTitleError.hidden = true;

    var result = true;

    if(checkSecid() == false){
        secidError.hidden=false;
        result=false;
    }
    if(checkRegnumber() == false){
        regnumberError.hidden=false;
        result=false;
    }
    if(checkName(document.getElementById("name")) == false){
        nameError.hidden=false;
        result=false;
    }
    if(checkName(document.getElementById("emitentTitle")) == false){
        emitentTitleError.hidden=false;
        result=false;
    }

    return result;
}

function validateHistoryForm(){
    var secidError = document.getElementById("secidError");
    var tradedateError = document.getElementById("tradedateError");
    var numtradesError = document.getElementById("numtradesError");
    var openError = document.getElementById("openError");
    var closeError = document.getElementById("closeError");

    secidError.hidden = true;
    tradedateError.hidden = true;
    numtradesError.hidden = true;
    openError.hidden = true;
    closeError.hidden = true;

    var result = true;

    if(checkSecid() == false){
        secidError.hidden=false;
        result=false;
    }
    if(checkTradedate(document.getElementById("tradedate")) == false){
        tradedateError.hidden=false;
        result=false;
    }
    if(checkNumtrades(document.getElementById("numtrades")) == false){
        numtradesError.hidden=false;
        result=false;
    }
    if(checkNumtrades(document.getElementById("open")) == false){
        openError.hidden=false;
        result=false;
    }
    if(checkNumtrades(document.getElementById("close")) == false){
        closeError.hidden=false;
        result=false;
    }

    return result;
}