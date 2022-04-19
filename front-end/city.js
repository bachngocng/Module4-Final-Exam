function getAllCity() {
    $.ajax({
        type: 'GET',
        url: `http://localhost:8080/cities`,
        success: function (cities) {
            let content = ``;
            for (let i = 0; i < cities.length; i++) {
                content += `  
        <tr>
        <td>${i + 1}</td>
        <td><a href="/front-end/detail-city.html?id=${cities[i].id}">${cities[i].name}</a></td>
        <td>${cities[i].country.name}</td>
       <td>${cities[i].area}</td>
        <td>${cities[i].population}</td>
        <td>${cities[i].gdp}</td>
        <td>${cities[i].description}</td>
        <td><button class="btn btn-primary" data-target="#edit-city" data-toggle="modal"
                                        type="button" onclick="showEditCity(${cities[i].id})"><i class="fa fa-unlock"></i></button>
            <button class="btn btn-danger" data-target="#delete-city" data-toggle="modal"
                                        type="button" onclick="showDeleteCity(${cities[i].id})"><i class="fa fa-lock"></i></button></td>
        </tr>`
            }
            $('#table-city').html(content);
        }
    })
    // event.preventDefault();
}
function createCity() {
    let name = $('#name').val();
    let country = $('#country').val();
    let area = $('#area').val();
    let population = $('#population').val();
    let gdp = $('#GDP').val();
    let description = $('#description').val();
    let city = {
        name: name,
        country: {
            id: country,
        },
        area: area,
        population: population,
        gdp: gdp,
        description: description
    }
    $.ajax({
        type: 'POST',
        url: `http://localhost:8080/cities`,
        data: JSON.stringify(city),
        headers:{
            'Accept':'application/json',
            'Content-type':'application/json'
        },
        success: function () {
            getAllCity();
            showSuccessMessage("tạo mới thành công");
        }, error: function () {
            showErrorMessage("Tạo mới lỗi");
        }
    })
}

function deleteCity(id) {
    $.ajax({
        type: 'DELETE', url: `http://localhost:8080/cities/${id}`, success: function () {
            getAllCity();
            showSuccessMessage('Xóa thành công!');
        }, error: function () {
            showErrorMessage('Xóa lỗi');
        }
    })
}

function showSuccessMessage(message) {
    $(function () {
        var Toast = Swal.mixin({
            toast: true, position: 'top-end', showConfirmButton: false, timer: 3000
        });

        Toast.fire({
            icon: 'success', title: message
        })
    });
}

function showErrorMessage(message) {
    $(function () {
        var Toast = Swal.mixin({
            toast: true, position: 'top-end', showConfirmButton: false, timer: 3000
        });

        Toast.fire({
            icon: 'error', title: message
        })
    });
}

function drawCountry() {
    $.ajax({
        type: 'GET', url: `http://localhost:8080/countries`, success: function (countries) {
            let content = `<option>Chọn quốc gia</option>`
            for (let country of countries) {
                content += `<option value="${country.id}">${country.name}</option>`
            }
            $('#country').html(content);
        }
    })
}

function showDeleteCity(id) {
    let content = `<button class="btn btn-secondary" data-dismiss="modal" type="button">Đóng</button>
                    <button class="btn btn-danger" onclick="deleteCity(${id})" type="button" aria-label="Close" class="close" data-dismiss="modal">Xóa</button>`;
    $('#footer-delete').html(content);
}

function editCity(id) {
    let name = $('#editName').val();
    let area = $('#editArea').val();
    let population = $('#editPopulation').val();
    let gdp = $('#editGDP').val();
    let description = $('#editDescription')
    let country = $('#editCountry').val();
    let city = {
        name: name, country: {
            name: name,
        }, area: area, population: population, gdp: gdp, description: description
    }
    city.append(name);
    city.append(country);
    city.append(area);
    city.append(population);
    city.append(GDP);
    city.append(description);
    $.ajax({
        type: 'PUT',
        url: `http://localhost:8080/cities/${id}`,
        data: JSON.stringify(city),
        success: function () {
            getAllCity();
            showSuccessMessage('Sửa thành công!');
        }, error: function () {
            showErrorMessage('sửa lỗi');
        }
    })
}

function showEditCity(id) {
    $.ajax({
        type: "GET", url: `http://localhost:8080/cities/${id}`, success: function (city) {
            $('#editName').val(city.name);
            $('#editCountry').val(city.country.id);
            $('#editArea').val(city.area);
            $('#editPopulation').val(city.population);
            $('#editGDP').val(city.gdp);
            $('#editDescription').val(city.description);
            $.ajax({
                type: 'GET',
                url: `http://localhost:8080/countries`,
                success: function (countries) {
                    let content = '';

                    if (city.country != null) {
                        content = `<option value="${city.country.id}">${city.country.name}</option>`;
                    } else {
                        content = `<option>Chọn Quốc Gia</option>`;
                    }
                    for (let city of countries) {
                        content += `<option value="${city.id}">${city.name}</option>`
                    };
                    $('#editCountry').html(content);
                }
            })

            let content = `<button class="btn btn-secondary" data-dismiss="modal" type="button">Đóng</button>
                    <button class="btn btn-primary" onclick="editCity(${id})" type="button" aria-label="Close" class="close" data-dismiss="modal">Chỉnh sửa</button>`;
            $('#edit-form').html(content);
        }
    })
}


$(document).ready(function () {
    getAllCity();
});

