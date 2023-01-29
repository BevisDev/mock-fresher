//Input dropdown component
let BTN = document.querySelector('button'),
    List = document.querySelector('datalist'),
    selectBox = document.querySelector('select'),
    input = document.querySelector('.dropdowninput'),
    options = selectBox.options;

BTN.onclick = () => {
    if (List.style.display === '') {
        List.style.display = 'block';
        BTN.innerHTML = "<i class='bi bi-caret-up-fill'></i>";
        let val = input.value;
        for (let i = 0; i < options.length; i++) {
            if (options[i].val === val) {
                selectBox.selectedIndex = i;
                break;
            }
        }
    } else HideSelectBox();
}

selectBox.addEventListener('change', () => {
    input.value = options[selectBox.selectedIndex].text;
    HideSelectBox();
});


input.addEventListener('focus', HideSelectBox);

function HideSelectBox() {
    List.style.display = '';
    BTN.innerHTML = "<i class='bi bi-caret-down-fill'></i>";
}





