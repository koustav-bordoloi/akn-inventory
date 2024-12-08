const apiItem = '/admin/item';

$(() => {
    let itemTbl;

    function initializeTable() { itemTbl = $("#itemTable").DataTable({ columnDefs: [{ targets: [0, 3, 4], className: "text-center" }] }); }

    function fetchAllCategories() {
        axiosInstance.get(`${apiItem}/all`)
            .then(response => {
                const rows = response.data.data.map((item, index) => [
                    index + 1,
                    item.name,
                    item.categoryName,
                    `<span class="badge bg-${item.status === 'ACTIVE' ? 'success' : 'danger'} rounded-pill">${item.status}</span>`,
                    `<button class="btn btn-outline-dark btn-sm editBtn" data-id="${item.id}"><i class="fa-regular fa-pen-to-square"></i> Edit</button>`
                ]);
                itemTbl.clear().rows.add(rows).draw();
            })
            .catch(() => mySwal.fire({
                icon: "error",
                title: "Error",
                text: "Failed to load categories. Please try again."
            }));
    }

    $("#saveItemBtn").on('click', () => {
        const formDataJson = formDataToJson(new FormData($("#itemForm")[0]));

        const isEditMode = !!formDataJson.id;

        axiosInstance.put(`${apiItem}/add`, formDataJson)
            .then(response => {
                mySwal.fire({
                    icon: "success",
                    title: "Success",
                    text: response.data.msg,
                    showConfirmButton: false,
                    timer: 1500
                });

                $('#itemModal').modal('hide');

                const item = response.data.data;
                const rowData = [
                    isEditMode ? formDataJson.id : itemTbl.rows().count() + 1,
                    item.name,
                    item.categoryName,
                    `<span class="badge bg-${item.status === 'ACTIVE' ? 'success' : 'danger'} rounded-pill">${item.status}</span>`,
                    `<button class="btn btn-outline-dark btn-sm editBtn" data-id="${item.id}"><i class="fa-regular fa-pen-to-square"></i> Edit</button>`
                ];

                const rowIndex = isEditMode
                    ? itemTbl.rows().indexes().toArray().find(index => String(itemTbl.row(index).data()[0]) === String(formDataJson.id))
                    : null;

                rowIndex !== undefined && rowIndex !== null
                    ? itemTbl.row(rowIndex).data(rowData).draw(false)
                    : itemTbl.row.add(rowData).draw(false);

            }).catch(error => {
                const errorDetails = handleError(error)
                if (errorDetails) {
                    displayFormErrors(errorDetails, "itemForm");
                }
            });
    });

    $(document).on('click', '.editBtn', function () {
        const id = $(this).data('id');

        axiosInstance.get(`${apiItem}/${id}`)
            .then(response => {
                const item = response.data.data;
                const itemForm = $("#itemForm")[0];

                Object.keys(item).forEach((key) => {
                    const element = itemForm.elements[key];
                    if (element) {
                        element.value = item[key];
                    }
                });
                $("#itemModal").modal("show");
            }).catch();
    })

    $("#itemModal").on("hidden.bs.modal", function () {
        $("#itemForm")[0].reset();
        $(".form-error").html("");
        $(".is-invalid").removeClass("is-invalid")
    })

    initializeTable();
    fetchAllCategories();
});
