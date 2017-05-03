<!DOCTYPE html>
<html>
    <head>
        <title>Collect'IF - Liste des Activités</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <script src="//code.jquery.com/jquery-1.12.1.min.js"></script>
    </head>
    <body>
        <h1>Collect'IF &ndash; Liste des Activités</h1>
        <div id='listeActivites'>
            <span>chargement en cours...</span>
        </div>


        <script>
            $(function () {
                $.ajax({
                    url: './ActionServlet',
                    type: 'POST',
                    data: {
                        todo: 'listAll'
                    },
                    dataType: 'json'
                }).done(function (data) {
                    var html = '<ul>';
                    for (var i = 0; i < data.length; i++) {
                        var s = (data[i].nbParticipants >= 2) ? 's' : '';
                        var gratuit = data[i].payant ? 'payant' : 'gratuit';
                        html += '<li><a href="activite/' + data[i].id +'">' + data[i].denomination + '</a> -> '
                                + data[i].nbParticipants + ' participant' + s + ', ' + gratuit + '</li>';
                        console.debug(data[i]);
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
    </body>
</html>
