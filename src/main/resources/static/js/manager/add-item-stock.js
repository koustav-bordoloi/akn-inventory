const apiManager = '/manager';

$(() => {
    const cache = {}; // Cache to store previously fetched results
    let debounceTimer;

    $("#itemName").on("input", function () {
        clearTimeout(debounceTimer); // Clear the previous debounce timer

        const query = $(this).val().trim().toLowerCase();
        if (!query) {
            $("#suggestions").empty().hide(); // Hide suggestions if input is empty
            return;
        }

        debounceTimer = setTimeout(() => {
            if (cache[query]) {
                // Use cached results
                displaySuggestions(cache[query]);
            } else {
                // Fetch results from backend

                axiosInstance.get(`${apiManager}/search?q=${query}`)
                    .then(response => {
                        const results = response.data;
                        cache[query] = results; // Store results in cache
                        displaySuggestions(results);
                    }).catch(error => console.error("Error fetching suggestions:", error));
            }
        }, 300); // 300ms debounce time
    });

    // Function to display suggestions
    function displaySuggestions(items) {
        let suggestionsHtml = "";
        items.forEach(item => {
            suggestionsHtml += `<li><a class="dropdown-item suggested-item d-flex justify-content-between align-items-center" data-item="${item}">
                                        <span class="item-text">${item}</span><i class="fa fa-arrow-right"></i></a></li>`;
        });

        if (suggestionsHtml) {
            $("#suggestions").html(suggestionsHtml).show(); // Show dropdown
        } else {
            $("#suggestions").empty().hide(); // Hide dropdown if no results
        }

        // Handle click on suggestion
        $(".dropdown-item").on("click", function (e) {
            e.preventDefault();
            $("#itemName").val($(this).text().trim());
            $("#suggestions").empty().hide(); // Hide suggestions after selection
        });
    }

    // Hide suggestions when clicking outside
    $(document).on("click", function (e) {
        if (!$(e.target).closest("#itemName, #suggestions").length) {
            $("#suggestions").empty().hide();
        }
    });

    $(document).on('click', '.suggested-item', function () {
        const item = $(this).data('item');
        axiosInstance.get(`${apiManager}/get-item-stock-details?item=${item}`)
            .then(response => {
                generateAddStockForm(response.data.data)
            }).catch(error => console.error("Error fetching suggestions:", error));

    })
    function generateAddStockForm(itemDetails) {
        const data =
            `<h5 class="mb-3">Item Details</h5>
            <hr>
            <form id="addStockForm">
                <input type="text" name="itemId" id="itemId" value="${itemDetails.itemId}" hidden>
                <div class="form-group">
                    <div class="col-12">Item Name: </div>
                    <div class="col-12 text-large"> ${itemDetails.itemName} </div>
                </div>
                <div class="form-group">
                    <div class="col-12">Item Category: </div>
                    <div class="col-12 text-large"> ${itemDetails.categoryName}</div>
                </div>
                <div class="form-group">
                    <div class="col-12">Available Qty: </div>
                    <div class="col-12 text-large"> ${itemDetails.quantity}</div>
                </div>
                <div class="form-group col-12">
                    <label for="quantity" class="mb-2">Enter Quantity: </label>
                    <div class="d-flex gap-2">
                        <div class="col-3">
                            <input type="text" name="quantity" id="quantity" autocomplete="off"
                                class="form-control text-center" placeholder="Quantity" min="0">
                        </div>
                        <button class="btn btn-outline-dark quantity-btn" type="button"
                            data-action="decrement" id="decrementQuantityBtn"> - </button>
                        <button class="btn btn-outline-dark quantity-btn" type="button"
                            data-action="increment" id="incrementQuantityBtn">+</button>
                    </div>
                    <small id="quantityError" class="form-error text-danger mt-2"></small>
                </div>
            </form>
            <div class="col-12">
                <button type="submit" id="addStockBtn" class="btn btn-dark btn-block mt-2"><i
                        class="fa-solid fa-plus me-2"></i>Add to Stock</button>
            </div>`;

            $("#itemDetailsCard").html(data)
    }

    $(document).on('click', '.quantity-btn', function () {
        let currentQty = parseInt($('#quantity').val()) || 0;
        let action = $(this).data('action');
        if (action === 'increment') {
            $('#quantity').val(currentQty + 1);
        } else if (action === 'decrement' && currentQty > 0) {
            $('#quantity').val(currentQty - 1);
        }
    });

    $(document).on('click', '#addStockBtn', () => {
        const formDataJson = formDataToJson(new FormData($("#addStockForm")[0]));

        axiosInstance.put(`${apiManager}/add-item-stock`, formDataJson)
            .then(response => {
                mySwal.fire({
                    icon: "success",
                    title: "Success",
                    text: response.data.msg,
                    showConfirmButton: false,
                    timer: 1500
                });

                generateAddStockForm(response.data.data)

            }).catch(error => {
                const errorDetails = handleError(error)
                if (errorDetails) {
                    displayFormErrors(errorDetails, "addStockForm");
                }
            })
    });

})