
<h1>Collect'IF &ndash; Liste des Activités</h1>
<div id='listeActivites'>
    <span>chargement en cours...</span>
</div>

<script>
    $(function () {
        $.ajax({
            url: './json/all',
            type: 'POST',
            dataType: 'json'
        }).done(function (data) {
            var html = '<ul>';
            for (var i = 0; i < data.length; i++) {
                var s = (data[i].nbParticipants >= 2) ? 's' : '';
                var gratuit = data[i].payant ? 'payant' : 'gratuit';
                html += '<li><a href="./' + data[i].id + '">' + data[i].denomination + '</a> -> '
                        + data[i].nbParticipants + ' participant' + s + ', ' + gratuit + '</li>';
            }
            html += '</ul>';
            $('#listeActivites').html(html);
        }).fail(function () {
            $('#listeActivites').html('ERREUR de chargement');
        }).always(function () {
            //
        });
    });
</script>
