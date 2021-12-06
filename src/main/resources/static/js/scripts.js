function updateTotalPrice() {
    let price = 0;
    let cartItems = document.querySelectorAll('#itemCount');
    let cartChecks = document.querySelectorAll('#cartCheck');
    let totalPrice = document.getElementById("totalPrice");
    for (let i = 0; i < cartItems.length; i++) {
        if (cartChecks[i].checked && cartItems[i].value !== "") {
            price += parseInt(cartItems[i].value) * parseInt(cartItems[i].name);
        }
    }
    totalPrice.innerText = splitPrice(price);
}

function splitPrice(price) {
    let priceString = price.toString();
    let i = priceString.length % 3;
    let result = priceString.substring(0, i);
    while (i < priceString.length) {
        if (i !== 0) {
            result += ',';
        }
        result += priceString.substring(i, i + 3);
        i += 3;
    }
    return result;
}

$(document).ready(function () {
    let cartCheck = $("input[name='cartCheck']");
    cartCheck.click(function () {
        if ($(this).is(":checked")) {
            $(this).parent().parent().css("background-color", "#f5f5f5");
        } else {
            $(this).parent().parent().css("background-color", "#ffffff");
        }
    });
    updateTotalPrice();

    let inputs = document.querySelectorAll('input');
    inputs.forEach(function (item) {
        item.addEventListener('input', updateTotalPrice);
    })
});

function checkout() {
    let userId = document.getElementById("userId");
    let cartItems = document.querySelectorAll('#itemCount');
    let cartChecks = document.querySelectorAll('#cartCheck');
    let price = 0;
    let itemIds = [];
    let itemCounts = [];
    for (let i = 0; i < cartItems.length; i++) {
        if (cartChecks[i].checked && cartItems[i].value !== "" && parseInt(cartItems[i].value) > 0) {
            price += parseInt(cartItems[i].value) * parseInt(cartItems[i].name);
            itemIds.push(parseInt(cartItems[i].alt));
            itemCounts.push(parseInt(cartItems[i].value));
        }
    }
    if (price === 0) {
        alert("결재할 상품을 선택해주세요");
    } else {
        $.ajax(
            {
                type: "POST",
                url: "/cart/checkout",
                data: JSON.stringify({
                    userId: userId.innerText,
                    itemIds: itemIds,
                    itemCounts: itemCounts
                }),
                dataType: 'json',
                processData: true,
                contentType: 'application/json',
                success: function (data) {
                    alert("장바구니에서 상품을 구매하였습니다.");
                    location.href = "/cart/history/" + userId.innerText;
                }
            }
        );
    }
}


function deleteItem(e) {
    let item = e.parentNode.parentNode;
    let userId = document.getElementById("userId");
    let cartCount = document.getElementById("cartCount");
    let count = parseInt(cartCount.innerText, 10);
    $.ajax(
        {
            type: "DELETE",
            url: "/item/cart/" + e.id,
            data: JSON.stringify({
                userId: userId.innerText
            }),
            dataType: 'json',
            processData: true,
            contentType: 'application/json',
            success: function (data) {
                item.parentNode.removeChild(item);
                count--;
                cartCount.innerText = count.toString();
            }
        }
    );
}

function addToCart(item) {
    let userId = document.getElementById("userId");
    let cartCount = document.getElementById("cartCount");
    let addCartLink = item;

    let count = parseInt(cartCount.innerText, 10);
    let text = addCartLink.innerText.trim();
    let itemCount = document.getElementById("inputQuantity").value;

    if (text === "장바구니 추가") {
        if (parseInt(itemCount) > 0) {
            $.ajax(
                {
                    type: "POST",
                    url: "/item/cart/" + item.id,
                    data: JSON.stringify({
                        userId: userId.innerText,
                        itemCount: parseInt(itemCount)
                    }),
                    dataType: 'json',
                    processData: true,
                    contentType: 'application/json',
                    success: function () {
                        count++;
                        addCartLink.innerText = "장바구니에서 삭제하기";
                        addCartLink.style.color = "#FFFFFF"
                        addCartLink.style.backgroundColor = "#212529";
                        alert("해당 상품을 장바구니에 담았습니다.");
                        cartCount.innerText = count.toString();
                    }
                }
            );
        }
    } else {
        $.ajax(
            {
                type: "DELETE",
                url: "/item/cart/" + item.id,
                data: JSON.stringify({
                    userId: userId.innerText
                }),
                dataType: 'json',
                processData: true,
                contentType: 'application/json',
                success: function () {
                    count--;
                    addCartLink.innerText = "장바구니 추가";
                    addCartLink.style.color = "#212529"
                    addCartLink.style.backgroundColor = "#FFFFFF";
                    alert("해당 상품을 장바구니에서 삭제했습니다.");
                    cartCount.innerText = count.toString();
                }
            }
        );
    }
}

