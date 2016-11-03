$("#newCategory").removeAttr("name");
$("#categoryButton").click(function() {
    $("#oldCategories, #categoryButton, #or, #categories").hide();
    $("#enter, #newCategory, #p").css("display", "inline-block");
    $("select").removeAttr("name");
    $("#newCategory").attr("value", "");
});