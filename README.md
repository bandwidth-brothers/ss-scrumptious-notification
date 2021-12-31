# ss-scrumptious-notification
#### Procedure to create/update/read/delete a template:
    connect to your Amazon CLI
    create a <template_name>.json file, such as OrderDetailsTemplate.json file.
    create template: aws ses create-template --cli-input-json file://OrderDetailsTemplate.json
    update template: aws ses update-template --cli-input-json file://OrderDetailsTemplate.json
    read template: aws ses get-template --template-name OrderDetailsTemplate
    delete template: aws ses delete-template --template-name OrderDetailsTemplate

    
