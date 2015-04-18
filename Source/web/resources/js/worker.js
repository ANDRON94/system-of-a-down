function addSpecialization(){
    var countTargets=$("#specializations").children().length;
    $.ajax({
        url:'/manager/addSpecialization',
        data:{count:countTargets},
        success: function(response){
            $("#specializations").append(response);
        }
    });
}
function removeSpecialization(){
    if($("#specialization").children().length!=1){
        $('#specialization li:last-child').remove();
    }
}