var currencyInput = document.querySelector('input[type="currency"]')
var currency = 'PLN'
onBlur({target:currencyInput})
currencyInput.addEventListener('focus', onFocus)
currencyInput.addEventListener('blur', onBlur)

function localStringToNumber( s ){
  return Number(String(s).replace(/[^0-9.-]+/g,""))
}

function onFocus(e){
  var value = e.target.value;
  e.target.value = value ? localStringToNumber(value) : ''
}

function onBlur(e){
  var value = e.target.value

  var options = {
      maximumFractionDigits : 2,
      currency              : currency,
      style                 : "currency",
      currencyDisplay       : "symbol"
  }

  e.target.value = (value || value === 0)
    ? localStringToNumber(value).toLocaleString(undefined, options)
    : ''
}