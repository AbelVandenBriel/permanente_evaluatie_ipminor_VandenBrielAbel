$(document).ready(function () {
    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        if (selectedOption != '') {
            var pathname = window.location.pathname;
            console.log(pathname);
            if (pathname.search(/\?lang=[a-z]*/i) !== -1) {
                pathname = pathname.replace(/\?lang=[a-z]*/i, "?lang=" + selectedOption);
                window.location.replace(pathname);
            }
            else {
                window.location.replace(pathname + "?lang=" + selectedOption)
            }
        }
    });
});