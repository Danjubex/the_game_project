$body = $("body");
var now = new Date();

var hoje ='2016-11-26';//""+(now.getFullYear())+"-"+(now.getMonth()+1)+"-"+now.getDate();//'2016-11-25'
//var hoje =""+(now.getFullYear())+"-"+(now.getMonth()+1)+"-"+now.getDate();//'2016-11-25'

//$(document).ready(function(){
//  $("body").addClass("loading");

//});

$(document).on({
    ajaxStart: function() { $body.addClass("loading"); },
    ajaxStop: function() { $body.removeClass("loading"); }
});

function produtividade_por_funcionario(valor)
{

    //alert('AAAAAAAAAAAAAAA');
    $('#painel_produtividade').empty('');
    $('#painel_produtividade').html('<h3 style="margin:15px;color:white;background-color:#29c75f;padding:10px;">Produtividade Individual</h3>');
    //$("body").addClass("loading");
    $.ajax({url: "../rfid/assets/php/get_func.php?data="+hoje, success: function(result){

      //alert(result);
      //if result is not empty
      var parsed = JSON.parse(result);
      var labels_temp = [];
      var series_temp = [];
      // alert(parsed[0]);
      //console.log('Tamanho: ' + parsed);
      parsed.forEach(function(valor)
      {
        labels_temp.push(valor[0].substring(4,6));//FUNC
        series_temp.push(valor[1]);//PRODU
        var cor = 'green';
        if(valor[1] < 60 && valor[1] > 30)
        {
            cor = 'yellow';
        }
        else if(valor[1] <= 30)
        {
          cor = 'orange';
        }

        $('#painel_produtividade').append(
          '<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12" style="margin-bottom:20px;">'+
              '<a class="card card-banner card-'+ cor + '-light">'+
              '<div class="card-body">'+
                '<i class="icon fa fa-user fa-4x"></i>'+
                '<div class="content">'+
                  '<div class="title"><b>Funcionário '+ valor[0].substring(4,6) +'</b></div>'+
                  '<div class="value"><span class="sign"></span>'+ valor[1] +'</div>'+
                '</div>'+
              '</div>'+
            '</a>'+
          '</div>'
        );

      });

      var data = {
        // A labels array that can contain any sort of values
        labels: [],
        // Our series array that contains series objects or in this case series data arrays
        series: [
            []
        ]
      };
      var options = {
      //  width: 300,
        height: 300,
        showArea: true,
        fullWidth: true,
        lineSmooth: false
      };

      data.labels = labels_temp;
      data.series[0] = series_temp;


      new Chartist.Line('.ct-chart', data, options);
    //  $("body").removeClass("loading");
    }});
}

function fichas_duplicadas(valor)
{
    $('#painel_duplicadas').empty('');
    $.ajax({url: "../rfid/assets/php/get_duplicadas.php?data="+hoje, success: function(result)
    {
        var parsed = JSON.parse(result);
        parsed.forEach(function(valor)
        {
          //alert(valor);//row
          //alert(valor[0]);//FUNC
          var data = valor[0];
          var ficha = valor[4]
          var funcionario = valor[1];
          var emissao = valor[2];
          $('#painel_duplicadas').append(
            '<tr>'
                +'<td>'+data+'</td>'
                +'<td>'+ficha+'</td>'
                +'<td>'+funcionario+'</td>'
                +'<td>'+emissao+'</td>'
            +'</tr>'
          );
        });

      }
    });
  }

  function fichas_repetidas(valor)
  {
      $('#painel_repetidas').empty('');
      $.ajax({url: "../rfid/assets/php/get_funcionario_repete_tag.php?data="+hoje, success: function(result)
      {
          var parsed = JSON.parse(result);
          parsed.forEach(function(valor)
          {
            //alert(valor);//row
            //alert(valor[0]);//FUNC
            var data1 = valor[0];
            var data2 = valor[1]
            //var valor = valor[2];
            var emissao = valor[4];
            var funcionario = valor[3];
            $('#painel_repetidas').append(
              '<tr>'
                  +'<td>'+data1+'</td>'
                  +'<td>'+data2+'</td>'
                  //+'<td>'+valor+'</td>'
                  +'<td>'+emissao+'</td>'
                  +'<td>'+funcionario+'</td>'
              +'</tr>'
            );
          });

        }
      });
    }
