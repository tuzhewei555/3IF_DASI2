
<h1>Collect'IF &ndash; Activité</h1>
<div id='activite'>
    <span>chargement en cours...</span>
</div>

<script>
    $(function () {
        let urlSplit = window.location.href.split("/");
        let id = urlSplit[urlSplit.length - 1];
        console.debug("id = " + id);
        $.ajax({
            url: './json/' + id,
            type: 'POST',
            dataType: 'json'
        }).done(function (activite) {
            var html = '<p>';
            html += activite.denomination + "<br/>";
            html += activite.payant + "<br/>";
            html += activite.nbParticipants + "<br/>";
            html += '</p>';
            $('#activite').html(html);
        }).fail(function () {
            $('#activite').html('ERREUR de chargement');
        }).always(function () {
            //
        });
    });
</script>
