import $ from 'jquery';

function search() {
    var container = $('#blogpostContainer');
    $.ajax({
        url: "/blogpost/search?q=" + $("#searchText").val(),
        type: "GET",
        dataType: "json",
    })
        .done(function (data) {
            document.getElementById("blogpostContainer").innerHTML = '';
            $(data).each(function (index, element) {
                //wrapper layer
                var searchElement = document.createElement('div');

                //card header variables && title
                var cardHeader = document.createElement('a');
                var title = document.createElement('h2');
                //set attributes
                cardHeader.setAttribute('href','/blog/'+element['id']);
                cardHeader.setAttribute('class','card-header');
                title.innerHTML = element['title'];
                //set hierarchies
                searchElement.appendChild(cardHeader);
                cardHeader.appendChild(title);

                //set Card body and his Hierarchy
                var cardBody = document.createElement('div');
                cardBody.setAttribute('class','card-body');
                searchElement.appendChild(cardBody);

                //set Author and Author Hierarchies
                var author = document.createElement('h6');
                author.innerHTML = 'Autore : '+element['author'];
                cardBody.appendChild(document.createElement('p'));
                cardBody.appendChild(author);
                cardBody.appendChild(document.createElement('p'));

                //set Category and Category Hierarchies
                var category = document.createElement('h6');
                category.innerHTML = 'Categoria : '+element['category'];
                cardBody.appendChild(document.createElement('p'));
                cardBody.appendChild(category);
                cardBody.appendChild(document.createElement('p'));

                //set Date and Date Hierarchies
                var date = document.createElement('h6');
                date.innerHTML = 'Data : '+element['date'];
                cardBody.appendChild(document.createElement('p'));
                cardBody.appendChild(date);
                cardBody.appendChild(document.createElement('p'));

                //set Text and Text Hierarchies
                var text = document.createElement('h6');
                text.innerHTML = 'Testo : '+element['text'];
                cardBody.appendChild(document.createElement('p'));
                cardBody.appendChild(text);
                cardBody.appendChild(document.createElement('p'));

                searchElement.setAttribute("class","card mb-4 shadow-sm");
                container.append(searchElement);
            });
        })
        .fail(function () {
            alert("errore");
        });
}

$(document).ready(function () {
    $("#refresh").click(function () {
        search();
    });
});

var searchText = document.getElementById("searchText")
searchText.addEventListener('input', function checkIf3Characters() {
    if(searchText.value.length >= 3)
        search();
})