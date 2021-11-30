function addToCart(item) {
    let userId = document.getElementById("userId");
    const cartCount = document.getElementById("cartCount");
    const addCartLink = item;

    let count = parseInt(cartCount.innerText, 10);
    let text = addCartLink.innerText.trim();
    let itemCount = document.getElementById("inputQuantity").value;

    if (text === "장바구니 추가") {
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

