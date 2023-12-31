
/* upload jpg */

const selectImage = document.querySelector('.select-image');
const inputFile = document.querySelector('#file');
const imgArea = document.querySelector('.img-area');

selectImage.addEventListener('click', function () {
	inputFile.click();
})

inputFile.addEventListener('change', function () {
	const image = this.files[0]
	if(image.size < 2000000) {
		const reader = new FileReader();
		reader.onload = ()=> {
			const allImg = imgArea.querySelectorAll('img');
			allImg.forEach(item=> item.remove());
			const imgUrl = reader.result;
			const img = document.createElement('img');
			img.src = imgUrl;
			imgArea.appendChild(img);
			imgArea.classList.add('active');
			imgArea.dataset.img = image.name;
		}
		reader.readAsDataURL(image);
	} else {
		alert("Image size more than 2MB");
	}
})

var currencyInput = document.querySelector('input[type="currency"]')
        var currency = 'PLN' // https://www.currency-iso.org/dam/downloads/lists/list_one.xml

        // format inital value
        onBlur({ target: currencyInput })

        // bind event listeners
        currencyInput.addEventListener('focus', onFocus)
        currencyInput.addEventListener('blur', onBlur)


        function localStringToNumber(s) {
            return Number(String(s).replace(/[^0-9.-]+/g, ""))
        }

        function onFocus(e) {
            var value = e.target.value;
            e.target.value = value ? localStringToNumber(value) : ''
        }

        function onBlur(e) {
            var value = e.target.value

            var options = {
                maximumFractionDigits: 2,
                currency: currency,
                style: "currency",
                currencyDisplay: "symbol"
            }

            e.target.value = (value || value === 0)
                ? localStringToNumber(value).toLocaleString(undefined, options)
                : ''
                 }
