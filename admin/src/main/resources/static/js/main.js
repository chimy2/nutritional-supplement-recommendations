/* modal */
const modalContainer = $('.modal-container')[0];

$(modalContainer).click((event) => {
    if (event.target === modalContainer) {
        closePopup();
    }
});

$(modalContainer)
    .find('.btn-modal-close')
    .click(() => {
        closePopup();
    });

function openPopup() {
    modalContainer.classList.remove('d-none');
}

function closePopup() {
    modalContainer.classList.add('d-none');
}

function openDeleteModal(seq) {
    const board = getBoardName();
    let boardName;

    switch (board) {
        case 'notice':
            boardName = '공지사항';
            break;
        case 'nutri':
            boardName = '영양제';
            break;
        case 'review':
            boardName = '리뷰';
            break;
        case 'admin':
            boardName = '관리자';
            break;
    }
    $('.modal-title').text(`${boardName} 삭제`);
    $('.modal-text').text(`해당 ${boardName}을(를) 삭제하시겠습니까?`);

    $(modalContainer).find('input[name="seq"]').val(seq);

    openPopup();
}

function getBoardName() {
    const pathname = location.pathname;
    return pathname.split('/')[1];
}

function returnPage() {
    history.back();
}


$(document).ready(function() {
    $('.select2-multiple').select2({
		placeholder: 'Select Categories'
	});
	
});

$('.nutri-datetimepicker').datetimepicker({
    format: 'MM/DD/YYYY hh:mm A',
    icons: {
        time: 'fa fa-clock', // 🕒 시간 선택 아이콘
    }
});

