"""Prepare a server-only copy of the existing ReNew WAR.

Usage: python3 prepare-server-war.py INPUT.war OUTPUT.war
The local WAR remains unchanged. No secret values are copied into the WAR.
"""
import sys
from pathlib import Path
from zipfile import ZipFile

if len(sys.argv) != 3:
    raise SystemExit("Usage: python3 prepare-server-war.py INPUT.war OUTPUT.war")

source, output = map(Path, sys.argv[1:])
if source.resolve() == output.resolve() or output.exists():
    raise SystemExit("Choose a new output filename.")

base = "WEB-INF/classes/application.properties"
server = "WEB-INF/classes/application-server.properties"

with ZipFile(source) as original:
    if not {base, server}.issubset(original.namelist()):
        raise SystemExit("Expected configuration files not found in WAR.")
    replacements = {
        base: original.read(base).decode("utf-8").rstrip()
        + "\nspring.profiles.active=server\n",
        server: original.read(server).decode("utf-8").rstrip()
        + "\nspring.config.import=file:/home/c2tb9p/.renew-server/application.properties\n",
    }
    with ZipFile(output, "x") as prepared:
        for entry in original.infolist():
            data = replacements[entry.filename].encode("utf-8") if entry.filename in replacements else original.read(entry)
            prepared.writestr(entry, data)
with ZipFile(output) as prepared:
    if prepared.testzip() is not None:
        raise SystemExit("Archive integrity test failed.")
print("Server WAR prepared:", output)
