param([string]$BaseUrl = 'http://localhost:8080')
$ErrorActionPreference = 'Stop'
$BaseUrl = $BaseUrl.TrimEnd('/')
function Assert-Status([string]$Path, [string]$Expected, [string]$Method = 'GET') {
    $status = curl.exe -sS -o NUL -w '%{http_code}' -X $Method "$BaseUrl$Path"
    if ($LASTEXITCODE -ne 0 -or $status -ne $Expected) {
        throw "$Method $Path expected $Expected, received $status"
    }
    Write-Host "PASS $Method $Path : $status"
}
Assert-Status '/' '200'
Assert-Status '/api/notebooks/1' '200'
Assert-Status '/messages' '302'
Assert-Status '/admin' '302'
# Anonymous DELETE is rejected before accessing data. ID 0 is not a catalogue record.
Assert-Status '/api/notebooks/0' '401' 'DELETE'
$notebook = Invoke-RestMethod "$BaseUrl/api/notebooks/1"
if ($notebook.id -ne 1 -or !$notebook.processor -or !$notebook.opsystem) {
    throw 'Joined notebook response is incomplete'
}
Write-Host 'PASS notebook 1 includes processor and operating system'
Write-Host 'Smoke tests finished. No administrator credentials or existing data changes required.'
