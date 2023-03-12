#!/usr/bin/env sh
if [[ -z "${RUN_COMMAND}" ]]; then
    echo "Starting $@...."
    exec "$@"
else
    echo "Running custom command...."
    sh -c "${RUN_COMMAND}"
fi