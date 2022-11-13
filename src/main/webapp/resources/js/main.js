$(".area-wrapper").on("click", (e) => {
    let rInput = $("input[name='values-form:rVal_input']");

    let k = $(".area-wrapper").width() / 2;
    let rVal = rInput.val().replace(",",".");
    let xVal = rVal * (e.offsetX - k) * 1.25 / k;
    let yVal = rVal * (k - e.offsetY) * 1.25 / k;

    $("input[name='values-form:xVal']").val(xVal);
    $("input[name='values-form:yVal']").val(yVal);
    $("input[name='values-form:addButton']").click();
})
