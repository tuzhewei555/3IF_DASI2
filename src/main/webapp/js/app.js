/**
 * app.js - En charge de la Single Webpage App
 */

var currentURL = '';

function update_links() {
    $('a').prop('onclick', null).off('click');
    $('a').on('click', function (e) {
        e.preventDefault();
        change_page($(this).attr('href'));
    });
}

function change_page(url) {
    currentURL = url;
    $.ajax({
        url: './vues/' + currentURL,
        type: "GET"
    }).done(function (data) {
        if (data) {
            $('#app').hide().html(data).fadeIn(300);
            update_links();
        } else {
            alert_error("Impossible de récupérer la page.");
        }
    }).fail(function () {
        alert_error("Impossible de récupérer la page.");
    });
}

function refresh_page() {
    console.debug('refresh');
    change_page(currentURL);
}

function set_title(title) {
    document.title = "Collect'IF - " + title;
    $('#page_title').html(title);
}

function get_query() {
    var splits = currentURL.split('?');
    return (splits.length > 1) ? splits[1] : '';
}

function alert_success(message) {
    $('#alert_error').collapse('hide');
    $('#alert_success_message').html(message);
    $('#alert_success').collapse('show');
}

function alert_error(message) {
    $('#alert_success').collapse('hide');
    $('#alert_error_message').html(message);
    $('#alert_error').collapse('show');
}

function alert_hide() {
    $('#alert_success').collapse('hide');
    $('#alert_error').collapse('hide');
}

$(document).ready(function () {
    $.ajaxSetup({cache: false});
    change_page('./index.html');
});
