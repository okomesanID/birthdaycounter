    $('input[type="number"]').focusout(function() {
        if(typeof $(this).attr('min') !== "undefined" && parseInt($(this).val()) < parseInt($(this).attr('min')))
            $(this).val($(this).attr('min'));
        else if(typeof $(this).attr('max') !== "undefined" && parseInt($(this).val()) > parseInt($(this).attr('max')))
            $(this).val($(this).attr('max'));
        else if(typeof $(this).attr('min') !== "undefined" && $(this).val() === '')
            $(this).val($(this).attr('min'));
    });