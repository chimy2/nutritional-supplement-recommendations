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

$(document).ready(function () {
    /* select2 */
    $('.select2-multiple').select2({
        placeholder: 'Select Categories',
    });
});

/* datetimepicker */
$('.nutri-datetimepicker').datetimepicker({
    format: 'MM/DD/YYYY hh:mm A',
    icons: {
        time: 'fa fa-clock', // 🕒 시간 선택 아이콘
    },
});

$('.admin-datetimepicker').datetimepicker({
    format: 'YYYY-MM-DD',
});

/* admin verify */
{
    /* admin id/email duplication check */
    {
        const id = $('.input-admin-id');
        const email = $('.input-admin-email');
        const errorTextId = $('.input-admin-id-error');
        const errorTextEmail = $('.input-admin-email-error');

        id.blur(function () {
            const value = $(this).val();
            const reg = /[a-zA-Z0-9]{4,12}/;

            if (value === '') {
                errorTextId.removeClass('d-none');
                errorTextId.text('아이디를 입력해주세요');
            } else if (value.length < 4 || value.length > 12) {
                errorTextId.removeClass('d-none');
                errorTextId.text('4~12자로 입력해주세요');
            } else if (!RegExp(reg).test(value)) {
                errorTextId.removeClass('d-none');
                errorTextId.text('영어와 숫자만 입력해주세요');
            } else {
                errorTextId.addClass('d-none');
                $.ajax({
                    url: `/api/admin/exists/id/${value}`,
                    method: 'GET',
                    success: (result) => {
                        errorTextId.removeClass('d-none');
                        errorTextId.text('존재하는 아이디입니다');
                        $(this).val('');
                    },
                    error: (error) => {
                        errorTextId.text('');
                    },
                });
            }
        });

        email.blur(function () {
            const value = $(this).val();
            const reg =
                /^[a-zA-Z0-9._%+-]{2,}@[a-zA-Z0-9.-]{2,}\.[a-zA-Z]{2,}$/;

            if (value === '') {
                errorTextEmail.removeClass('d-none');
                errorTextEmail.text('이메일을 입력해주세요');
            } else if (!RegExp(reg).test(value)) {
                errorTextEmail.removeClass('d-none');
                errorTextEmail.text('이메일 형식을 확인해주세요');
            } else {
                errorTextEmail.addClass('d-none');
                $.ajax({
                    url: `/api/admin/exists/email/${value}`,
                    method: 'GET',
                    success: (result) => {
                        errorTextEmail.removeClass('d-none');
                        errorTextEmail.text('존재하는 이메일입니다');
                        $(this).val('');
                    },
                    error: (error) => {
                        errorTextEmail.text('');
                    },
                });
            }
        });
    }
    /* admin auth checkbox control */
    /* 
        슈퍼 관리 버튼
        O - 각 관리 버튼을 활성화한다 + 하위 권한도 활성화(네임 없이) + 올 권한은 checked, 네임은 지워야함
        X - 각 관리 버튼을 비활성화한다 + 하위 권한도 비활성화(네임 있어도 상관 없고 없어도 상관 없음) + 올권한 checked false

        관리 버튼
        O - 하위 관리 버튼을 활성화 한다 + 올 권한은 checked, 네임 추가
        X - 하위 관리 버튼을 비활성화 한다(네임 있어도 상관 없고 없어도 상관 없음) + 올권한 checked false

        하위 권한버튼
        - 조회 버튼
        O - 조회가 활성화되지 않은 상태라면 관리되지 않은 권한이므로 관리버튼 활성화 + name 붙이기
        X - 조회를 비활성화 시키면 모든 권한을 없애는 것이기 때문에 관리버튼을 비활성화하는 걸로 대체

        - 조회버튼이 아님
        O - 조회 버튼이 비활성화된 상태라면 trigger 시킨 후 모든 하위 버튼이 활성화 되면 올버튼 활성화 ( + 모든 올 권한이 활성화 됐다면 name 지우기, 슈퍼 관리 활성화)
        X - 올버튼이 눌린 상태라면 trigger 시켜주기 ( + 슈퍼 관리가 활성화 상태라면 비활성화, 다른 올버튼 활성화)

        올 버튼
        O - 하위 관리 버튼에 name 없애기 + 본인에게 name 붙이기
        X - 하위 관리 버튼에 name 붙이기

        염두에 둘 것 
        All 권한을 가진다면 하위 권한에는 name이 없어야 함 
        Why? name을 가지고 checked 값을 가진다면 서버에 전송되기 때문에
        같은 맥락으로 슈퍼 권한을 가진다면 ALL 권한의 name이 없어야 함
    */
    const superManage = document.getElementById('btnSuperManage');
    const noticeManage = document.getElementById('btnNoticeManage');
    const nutriManage = document.getElementById('btnNutriManage');
    const reviewManage = document.getElementById('btnReviewManage');
    const adminManage = document.getElementById('btnAdminManage');

    const noticeAll = document.querySelector('.btn-check-notice-all');
    const nutriAll = document.querySelector('.btn-check-nutri-all');
    const reviewAll = document.querySelector('.btn-check-review-all');
    const adminAll = document.querySelector('.btn-check-admin-all');
    const everyAll = [noticeAll, nutriAll, reviewAll, adminAll];

    const noticeAuths = document.querySelectorAll('.btn-check-notice');
    const nutriAuths = document.querySelectorAll('.btn-check-nutri');
    const reviewAuths = document.querySelectorAll('.btn-check-review');
    const adminAuths = document.querySelectorAll('.btn-check-admin');

    superManage.addEventListener('change', controlSuperManageButton);

    noticeManage.addEventListener('change', controlManageButton);
    nutriManage.addEventListener('change', controlManageButton);
    reviewManage.addEventListener('change', controlManageButton);
    adminManage.addEventListener('change', controlManageButton);

    noticeAuths.forEach((auth) =>
        auth.addEventListener('change', controlAuthsButton)
    );
    nutriAuths.forEach((auth) =>
        auth.addEventListener('change', controlAuthsButton)
    );
    reviewAuths.forEach((auth) =>
        auth.addEventListener('change', controlAuthsButton)
    );
    adminAuths.forEach((auth) =>
        auth.addEventListener('change', controlAuthsButton)
    );

    noticeAll.addEventListener('change', toggleAuthsName);
    nutriAll.addEventListener('change', toggleAuthsName);
    reviewAll.addEventListener('change', toggleAuthsName);
    adminAll.addEventListener('change', toggleAuthsName);

    function controlSuperManageButton() {
        const isChecked = this.checked;

        triggerCheckboxChange(noticeManage, isChecked);
        triggerCheckboxChange(nutriManage, isChecked);
        triggerCheckboxChange(reviewManage, isChecked);
        triggerCheckboxChange(adminManage, isChecked);
    }

    function controlManageButton() {
        const isChecked = this.checked;
        const className = this.id.replace(/btn|Manage/g, '').toLowerCase();
        const all = document.querySelector(`.btn-check-${className}-all`);
        const auths = document.querySelectorAll(`.btn-check-${className}`);

        superManage.checked = false;
        triggerCheckboxChange(all, isChecked);

        auths.forEach((auth) => {
            if (isChecked) {
                auth.checked = true;
            } else {
                auth.checked = false;
            }
        });

        if (
            noticeManage.checked &&
            nutriManage.checked &&
            reviewManage.checked &&
            adminManage.checked
        ) {
            superManage.checked = true;
        }
    }

    function controlAuthsButton() {
        const isChecked = this.checked;
        const isRead = this.id.includes('Read');
        const originalClassName = this.id.replace(
            /btn|Read|Create|Update|Delete/g,
            ''
        );
        const className = originalClassName.toLowerCase();
        const manage = document.getElementById(`btn${originalClassName}Manage`);
        const all = document.querySelector(`.btn-check-${className}-all`);
        const auths = Array.from(
            document.querySelectorAll(`.btn-check-${className}`)
        );

        if (isRead) {
            if (isChecked && !manage.checked) {
                triggerCheckboxChange(all, false);
                manage.checked = true;
            } else {
                triggerCheckboxChange(manage, false);
            }
        } else {
            if (isChecked) {
                const authRead = auths.find((auth) => auth.id.includes('Read'));

                if (!authRead.checked) {
                    triggerCheckboxChange(authRead, true);
                }

                const isCheckedAuths = auths.every((auth) => auth.checked);

                if (isCheckedAuths) {
                    triggerCheckboxChange(all, true);

                    const isEveryAllChecked = everyAll.every(
                        (all) => all.checked
                    );

                    if (isEveryAllChecked) {
                        superManage.checked = true;
                    }
                }
            } else {
                if (all.checked) {
                    triggerCheckboxChange(all, false);
                }
                if (superManage.checked) {
                    superManage.checked = false;
                    everyAll.forEach((allCheck) => {
                        if (allCheck != all) {
                            triggerCheckboxChange(allCheck, true);
                        }
                    });
                }
            }
        }
    }

    function toggleAuthsName() {
        const isChecked = this.checked;
        const className = this.dataset.target;
        const elements = document.querySelectorAll(`.${className}`);

        if (isChecked) {
            this.name = 'auths';
        } else {
            this.name = '';
        }

        elements.forEach((element) => {
            if (!isChecked) {
                element.name = 'auths';
            } else {
                element.name = '';
            }
        });

        if (everyAll.every((all) => all.checked)) {
            everyAll.forEach((all) => {
                all.name = '';
            });
        }
    }

    function triggerCheckboxChange(checkbox, isChecked) {
        checkbox.checked = isChecked;
        checkbox.dispatchEvent(new Event('change'));
    }
}
